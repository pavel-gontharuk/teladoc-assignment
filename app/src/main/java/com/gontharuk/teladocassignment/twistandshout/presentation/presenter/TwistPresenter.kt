package com.gontharuk.teladocassignment.twistandshout.presentation.presenter

import com.gontharuk.teladocassignment.core.lifecycle.Lifecycle
import com.gontharuk.teladocassignment.core.log
import com.gontharuk.teladocassignment.core.observer.Observable
import com.gontharuk.teladocassignment.core.observer.Observer
import com.gontharuk.teladocassignment.core.observer.Publisher
import com.gontharuk.teladocassignment.core.presentation.Presenter
import com.gontharuk.teladocassignment.core.threads.ThreadComposition
import com.gontharuk.teladocassignment.twistandshout.data.repository.TwistRepository
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistItem
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistUiState

class TwistPresenter(
    initState: TwistUiState,
    private val twistRepository: TwistRepository
) : Presenter<TwistUiState>, Observer<Lifecycle> {

    private val _state: Publisher<TwistUiState> = Publisher(initState)
    override val state: Observable<TwistUiState> get() = _state

    private val threads = ThreadComposition()

    override fun onNext(data: Lifecycle) {
        when (data) {
            Lifecycle.CREATE -> {
                when (val mState = _state.data) {
                    is TwistUiState.Loading -> search(mState.search)
                    is TwistUiState.Show -> Unit
                }
            }

            Lifecycle.STOP -> threads.interrupt()
            Lifecycle.DESTROY -> _state.clear()
            Lifecycle.START,
            Lifecycle.RESUME,
            Lifecycle.PAUSE,
            Lifecycle.NONE -> Unit
        }
    }

    fun search(search: String = "") {
        _state.next(TwistUiState.Loading(search))
        threads.add(
            onBackground = {
                twistRepository.search(search)
            },
            onMain = { result ->
                result.onSuccess { response ->
                    response.list
                        .map { TwistItem(it) }
                        .also {
                            _state.next(
                                TwistUiState.Show(
                                    search = search,
                                    items = it,
                                )
                            )
                        }
                }
                result.onFailure {
                    log("onFailure : ${it.cause}")
                    it.printStackTrace()
                }
            }
        )
    }
}