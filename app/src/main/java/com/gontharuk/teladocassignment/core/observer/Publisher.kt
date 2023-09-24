package com.gontharuk.teladocassignment.core.observer

class Publisher<T> : Observable<T> {

    private val observers: ArrayList<Observer<T>> by lazy { ArrayList() }
    private var data: T? = null

    override fun subscribe(observer: Observer<T>) {
        observers.add(observer)
        data?.also { observer.onItem(it) }
    }

    override fun unsubscribe(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun next(next: T) {
        data = next
        observers.forEach { it.onItem(data!!) }
    }

    fun clear() {
        observers.clear()
    }
}