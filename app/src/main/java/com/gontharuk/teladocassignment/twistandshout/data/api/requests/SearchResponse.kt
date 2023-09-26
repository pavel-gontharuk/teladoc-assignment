package com.gontharuk.teladocassignment.twistandshout.data.api.requests

import com.gontharuk.teladocassignment.twistandshout.data.rest.ResponseFactory
import com.gontharuk.teladocassignment.twistandshout.data.entity.CollectionServerModel
import com.gontharuk.teladocassignment.twistandshout.data.entity.CollectionServerModelFactory
import org.json.JSONObject

data class SearchResponse(
    val list: List<CollectionServerModel>
) {

    class Factory : ResponseFactory<SearchResponse> {

        override fun create(json: JSONObject): SearchResponse {
            val modelFactory = CollectionServerModelFactory()
            val array = json.getJSONArray("results")
            val list = mutableListOf<CollectionServerModel>()
            for (index in 0 until array.length()) {
                modelFactory.create(array.getJSONObject(index)).also {
                    list.add(it)
                }
            }
            return SearchResponse(list)
        }
    }
}