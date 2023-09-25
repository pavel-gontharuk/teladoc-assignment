package com.gontharuk.teladocassignment.core.observer

interface Observable<T> {

    val data: T?

    fun subscribe(observer: Observer<T>)

    fun unsubscribe(observer: Observer<T>)
}