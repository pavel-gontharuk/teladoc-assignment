package com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter

import com.gontharuk.teladocassignment.core.lifecycle.Lifecycle
import com.gontharuk.teladocassignment.core.observer.Observable
import com.gontharuk.teladocassignment.core.observer.Observer
import com.gontharuk.teladocassignment.core.observer.Publisher
import com.gontharuk.teladocassignment.core.presentation.Presenter
import com.gontharuk.teladocassignment.core.threads.ThreadComposition
import com.gontharuk.teladocassignment.romeoandjuliet.data.repository.WordsCountRepository
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordFiler
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItem
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState

class WordsCountPresenter(
    private val wordsCountRepository: WordsCountRepository,
    initState: WordsCountUiState
) : Presenter<WordsCountUiState>, Observer<Lifecycle> {

    private val _state: Publisher<WordsCountUiState> = Publisher(initState)
    override val state: Observable<WordsCountUiState> get() = _state

    private val composition = ThreadComposition()

    override fun onNext(data: Lifecycle) {
        when (data) {
            Lifecycle.CREATE -> {
                if (_state.data is WordsCountUiState.Loading) {
                    fetch(_state.data.filter)
                }
            }

            Lifecycle.STOP -> composition.interrupt()
            Lifecycle.DESTROY -> _state.clear()
            Lifecycle.START,
            Lifecycle.RESUME,
            Lifecycle.PAUSE,
            Lifecycle.NONE -> Unit
        }
    }

    fun fetch(filter: WordFiler) {
        composition.add(
            onBackground = {
                wordsCountRepository.words()
                    .map {
                        WordsCountItem(it)
                    }
                    .let { result ->
                        when (filter) {
                            WordFiler.COUNT -> result.sortedByDescending { it.count }
                            WordFiler.ALPHABETICALLY -> result.sortedBy { it.word }
                            WordFiler.LENGTH -> result.sortedByDescending { it.word.length }
                        }
                    }
            },
            onMain = {
                _state.next(
                    WordsCountUiState.Show(
                        filter = filter,
                        items = it
                    )
                )
            }
        )
    }
}