package com.gontharuk.teladocassignment.twistandshout.data.rest

import org.json.JSONObject

interface ResponseFactory<out T> {

    fun create(json: JSONObject): T
}