package com.gontharuk.teladocassignment.twistandshout.data.request

abstract class Request<out T>(
    private val method: Method,
    val query: String = ""
) {

    val requestMethod: String get() = method.value

    abstract val responseFactory: ResponseFactory<T>
}