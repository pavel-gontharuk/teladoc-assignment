package com.gontharuk.teladocassignment.twistandshout.data.api

import com.gontharuk.teladocassignment.twistandshout.data.entity.CollectionServerModel
import com.gontharuk.teladocassignment.twistandshout.data.entity.CollectionServerModelFactory
import com.gontharuk.teladocassignment.twistandshout.data.request.ResponseFactory
import org.json.JSONObject

data class SearchBeatlesResponse(
    val list: List<CollectionServerModel>
) {

    class Factory : ResponseFactory<SearchBeatlesResponse> {

        override fun create(json: JSONObject): SearchBeatlesResponse {
            val modelFactory = CollectionServerModelFactory()
            val array = json.getJSONArray("results")
            val list = mutableListOf<CollectionServerModel>()
            for (index in 0 until array.length()) {
                modelFactory.create(array.getJSONObject(index)).also {
                    list.add(it)
                }
            }
            return SearchBeatlesResponse(list)
        }
    }
}