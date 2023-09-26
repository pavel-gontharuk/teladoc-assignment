package com.gontharuk.teladocassignment.twistandshout.data.api

import com.gontharuk.teladocassignment.twistandshout.data.api.requests.SearchRequest
import com.gontharuk.teladocassignment.twistandshout.data.api.requests.SearchResponse
import com.gontharuk.teladocassignment.twistandshout.data.repository.TwistRepository
import com.gontharuk.teladocassignment.twistandshout.data.rest.ConnectionService

class TwistRepositoryImpl(
    private val service: ConnectionService
) : TwistRepository {

    override fun search(search: String): Result<SearchResponse> = service
        .connect(SearchRequest(search))
}