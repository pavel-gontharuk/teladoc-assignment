package com.gontharuk.teladocassignment.core.mapper

import org.json.JSONObject

interface JsonMapper<T> {

    fun map(data: T): JSONObject

    fun map(json: JSONObject): T
}