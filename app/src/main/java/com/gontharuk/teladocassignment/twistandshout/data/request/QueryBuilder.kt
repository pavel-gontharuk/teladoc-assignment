package com.gontharuk.teladocassignment.twistandshout.data.request

class QueryBuilder {

    private val map: MutableMap<String, String> = mutableMapOf()

    private var _path: String = ""

    fun path(path: String) = this.apply { _path = path }

    fun query(key: String, value: String) = this.apply { map[key] = value }

    fun build(): String = StringBuilder().apply {
        append(_path)
        map.toList().forEachIndexed { index, pair ->
            if (index == 0) append("?")
            else append("&")
            append("${pair.first}=${pair.second}")
        }
    }.toString()
}