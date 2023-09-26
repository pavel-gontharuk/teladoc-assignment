package com.gontharuk.teladocassignment.twistandshout.data.api

import com.gontharuk.teladocassignment.twistandshout.data.request.Method
import com.gontharuk.teladocassignment.twistandshout.data.request.QueryBuilder
import com.gontharuk.teladocassignment.twistandshout.data.request.Request
import com.gontharuk.teladocassignment.twistandshout.data.request.ResponseFactory

class SearchBeatlesRequest : Request<SearchBeatlesResponse>(
    method = Method.GET,
    query = QueryBuilder()
        .path("/search")
        .query("term", "thebeatles")
        .query("media", "music")
        .query("entity", "album")
        .query("attribute", "artistTerm")
        .build(),
) {

    override val responseFactory: ResponseFactory<SearchBeatlesResponse> = SearchBeatlesResponse.Factory()
}