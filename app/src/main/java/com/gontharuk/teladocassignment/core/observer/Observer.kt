package com.gontharuk.teladocassignment.core.observer

fun interface Observer<T> {

    fun onNext(data: T)
}