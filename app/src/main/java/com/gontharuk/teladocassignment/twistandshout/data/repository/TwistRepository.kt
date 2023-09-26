package com.gontharuk.teladocassignment.twistandshout.data.repository

import com.gontharuk.teladocassignment.twistandshout.data.api.requests.SearchResponse

interface TwistRepository {

    fun search(search: String): Result<SearchResponse>
}