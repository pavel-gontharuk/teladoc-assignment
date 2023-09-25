package com.gontharuk.teladocassignment.core.state

import android.os.Bundle

interface SaveStateManager<T> {

    fun save(state: T, outState: Bundle)

    fun restore(savedState: Bundle?): T
}