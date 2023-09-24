package com.gontharuk.teladocassignment.core

import android.os.Handler
import android.os.Looper
import java.util.LinkedList

class ThreadComposition {

    private val threads: LinkedList<Thread> by lazy { LinkedList() }

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
    }
}