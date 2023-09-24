package com.gontharuk.teladocassignment.core

import com.gontharuk.teladocassignment.core.observer.Observable

interface Presenter<T> {

    val stateObservable: Observable<T>

    fun attach()//todo lifecycle

    fun detach()//todo lifecycle
}