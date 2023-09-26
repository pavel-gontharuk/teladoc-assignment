package com.gontharuk.teladocassignment.twistandshout.data.rest

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ConnectionService(
    private val baseUrl: String,
    private val readTimeout: Int,
    private val connectTimeout: Int
) {

    fun <T> connect(request: Request<T>): Result<T> = try {
        val url = URL("$baseUrl${request.query}")
        val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = request.requestMethod
        connection.readTimeout = readTimeout
        connection.connectTimeout = connectTimeout
        connection.connect()

        val responseBuilder = StringBuilder()
        when (connection.responseCode) {
            HttpsURLConnection.HTTP_OK,
            HttpsURLConnection.HTTP_CREATED,
            HttpsURLConnection.HTTP_ACCEPTED -> connection.inputStream

            else -> connection.errorStream
        }.let {
            BufferedReader(InputStreamReader(it))
        }.also { reader ->
            reader.lines().forEach {
                responseBuilder.append(it)
            }
        }
        val json = JSONObject(responseBuilder.toString())

        when (connection.responseCode) {
            HttpsURLConnection.HTTP_OK,
            HttpsURLConnection.HTTP_CREATED,
            HttpsURLConnection.HTTP_ACCEPTED -> Result.success(request.responseFactory.create(json))

            else -> Result.failure(Throwable(json.toString()))
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.failure(ex)
    }
}