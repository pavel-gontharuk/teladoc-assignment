package com.gontharuk.teladocassignment.twistandshout.data.api.requests

import com.gontharuk.teladocassignment.twistandshout.data.rest.Method
import com.gontharuk.teladocassignment.twistandshout.data.rest.QueryBuilder
import com.gontharuk.teladocassignment.twistandshout.data.rest.Request
import com.gontharuk.teladocassignment.twistandshout.data.rest.ResponseFactory

class SearchRequest(
    search: String
) : Request<SearchResponse>(
    method = Method.GET,
    query = QueryBuilder()
        .path("/search")
        .query("term", search)
        .query("media", "music")
        .query("entity", "album")
        .query("attribute", "artistTerm")
        .build(),
) {

    override val responseFactory: ResponseFactory<SearchResponse> = SearchResponse.Factory()
}