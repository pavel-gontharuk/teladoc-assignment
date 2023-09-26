package com.gontharuk.teladocassignment.twistandshout.data.api

import com.gontharuk.teladocassignment.twistandshout.data.request.ConnectionService

object TwistApi {

    private val service = ConnectionService(
        baseUrl = "https://itunes.apple.com",
        readTimeout = 10_000,
        connectTimeout = 10_000,
    )

    fun searchBeatles(): Result<SearchBeatlesResponse> = service
        .connect(SearchBeatlesRequest())
}