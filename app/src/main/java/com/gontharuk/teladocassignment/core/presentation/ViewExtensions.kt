package com.gontharuk.teladocassignment.core.presentation

import android.os.Handler
import android.os.Looper
import android.view.View

inline fun View.debouncedListener(crossinline action: () -> Unit) = setOnClickListener {
    isEnabled = false
    action()
    Handler(Looper.getMainLooper())
        .postDelayed({
            isEnabled = true
        }, 300)
}
