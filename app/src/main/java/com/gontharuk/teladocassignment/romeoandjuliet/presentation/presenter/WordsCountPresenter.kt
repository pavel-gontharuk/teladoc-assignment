package com.gontharuk.teladocassignment.romeoandjuliet.presentation.presenter

import com.gontharuk.teladocassignment.core.Presenter
import com.gontharuk.teladocassignment.core.ThreadComposition
import com.gontharuk.teladocassignment.core.observer.Observable
import com.gontharuk.teladocassignment.core.observer.Publisher
import com.gontharuk.teladocassignment.romeoandjuliet.domain.usecase.WordsCountUseCase
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountItem
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity.WordsCountUiState

class WordsCountPresenter(
    private val wordsCountUseCase: WordsCountUseCase
) : Presenter<WordsCountUiState> {

    private val _stateObservable: Publisher<WordsCountUiState> = Publisher()//todo clear
    override val stateObservable: Observable<WordsCountUiState> get() = _stateObservable

    private val composition = ThreadComposition()

    override fun attach() {
        fetch()
    }

    override fun detach() {
        composition.interrupt()
    }

    private fun fetch() {
        composition.add(
            onBackground = {
                wordsCountUseCase.words()
                    .map {
                        WordsCountItem(
                            word = it.key,
                            count = it.value
                        )
                    }.sortedByDescending {//todo quicksort
                        it.count
                    }
            },
            onMain = {
                _stateObservable.next(WordsCountUiState.Show(it))
            }
        )
    }
}