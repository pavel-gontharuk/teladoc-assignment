package com.gontharuk.teladocassignment.core.presentation

import com.gontharuk.teladocassignment.core.observer.Observable

interface Presenter<T> {

    val state: Observable<T>
}