package com.gontharuk.teladocassignment.core.threads

import android.os.Handler
import android.os.Looper

class ThreadComposition {

    private val threads: MutableList<Thread> by lazy { mutableListOf() }

    fun <T> add(
        onBackground: () -> T,
        onMain: (T) -> Unit
    ) {
        Thread {
            val result = onBackground()
            Handler(Looper.getMainLooper()).post {
                onMain(result)
            }
        }.also {
            threads.add(it)
            it.start()
        }
    }

    fun interrupt() {
        threads.forEach {
            it.interrupt()
        }
        threads.clear()
    }
}