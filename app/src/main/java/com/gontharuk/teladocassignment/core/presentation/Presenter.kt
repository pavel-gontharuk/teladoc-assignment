package com.gontharuk.teladocassignment.core.presentation

import com.gontharuk.teladocassignment.core.observer.Observable

interface Presenter<T> {

    val stateObservable: Observable<T>
}