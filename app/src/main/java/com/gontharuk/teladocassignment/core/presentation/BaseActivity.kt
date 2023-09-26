package com.gontharuk.teladocassignment.core.presentation

import android.app.Activity
import android.os.Bundle
import com.gontharuk.teladocassignment.core.lifecycle.Lifecycle
import com.gontharuk.teladocassignment.core.observer.Observable
import com.gontharuk.teladocassignment.core.observer.Publisher

abstract class BaseActivity : Activity() {

    private val _lifecycle: Publisher<Lifecycle> = Publisher(Lifecycle.NONE)
    val lifecycle: Observable<Lifecycle> get() = _lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _lifecycle.next(Lifecycle.CREATE)
    }

    override fun onStart() {
        super.onStart()
        _lifecycle.next(Lifecycle.START)
    }

    override fun onResume() {
        super.onResume()
        _lifecycle.next(Lifecycle.RESUME)
    }

    override fun onPause() {
        super.onPause()
        _lifecycle.next(Lifecycle.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        _lifecycle.next(Lifecycle.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        _lifecycle.next(Lifecycle.DESTROY)
        _lifecycle.clear()
    }
}