package com.gontharuk.teladocassignment.core.observer

class Publisher<T>(initial: T? = null) : Observable<T> {

    private val observers: ArrayList<Observer<T>> by lazy { ArrayList() }

    private var _data: T? = initial
    override val data: T? get() = _data

    override fun subscribe(observer: Observer<T>) {
        observers.add(observer)
        _data?.also { observer.onNext(it) }
    }

    override fun unsubscribe(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun next(next: T) {
        _data = next
        observers.forEach { it.onNext(_data!!) }
    }

    fun clear() {
        observers.clear()
    }
}