package com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter

import com.gontharuk.teladocassignment.core.lifecycle.Lifecycle
import com.gontharuk.teladocassignment.core.observer.Observable
import com.gontharuk.teladocassignment.core.observer.Observer
import com.gontharuk.teladocassignment.core.observer.Publisher
import com.gontharuk.teladocassignment.core.presentation.Presenter
import com.gontharuk.teladocassignment.core.threads.ThreadComposition
import com.gontharuk.teladocassignment.romeoandjuliet.domain.usecase.WordsCountUseCase
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItem
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState

class WordsCountPresenter(
    private val wordsCountUseCase: WordsCountUseCase,
    initState: WordsCountUiState
) : Presenter<WordsCountUiState>, Observer<Lifecycle> {

    private val _state: Publisher<WordsCountUiState> = Publisher(initState)
    override val state: Observable<WordsCountUiState> get() = _state

    private val composition = ThreadComposition()

    override fun onNext(data: Lifecycle) {
        when (data) {
            Lifecycle.CREATE -> fetch()
            Lifecycle.STOP -> composition.interrupt()
            Lifecycle.DESTROY -> _state.clear()
            Lifecycle.START,
            Lifecycle.RESUME,
            Lifecycle.PAUSE -> Unit
        }
    }

    private fun fetch() {
        if (_state.data is WordsCountUiState.Show) return
        composition.add(
            onBackground = {
                wordsCountUseCase.words()
                    .map {
                        WordsCountItem(it)
                    }
                    .sortedByDescending { it.count }
            },
            onMain = {
                _state.next(WordsCountUiState.Show(it))
            }
        )
    }
}