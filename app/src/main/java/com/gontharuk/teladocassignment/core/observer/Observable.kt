package com.gontharuk.teladocassignment.core.observer

interface Observable<T> {

    fun subscribe(observer: Observer<T>)

    fun unsubscribe(observer: Observer<T>)
}