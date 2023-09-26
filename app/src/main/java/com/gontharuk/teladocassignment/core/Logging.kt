package com.gontharuk.teladocassignment.core

import android.util.Log

fun <T> T.log(message: String = ""): T = this.also {
    val field: String = if (it is String) it else it.toString()
    val stringBuilder = StringBuilder()
    val trace = (if (message.isNotEmpty()) 1 else 2).let { index ->
        Throwable().stackTrace[index]
    }
    stringBuilder.append(trace.className.split(".").last())
    stringBuilder.append(".${trace.methodName}")
    stringBuilder.append(":${trace.lineNumber}")
    if (message.isNotEmpty()) stringBuilder.append(" < $message")
    if (field.isNotEmpty() && field.isNotBlank()) stringBuilder.append(" > $field")
    Log.d("KEK", stringBuilder.toString())
}