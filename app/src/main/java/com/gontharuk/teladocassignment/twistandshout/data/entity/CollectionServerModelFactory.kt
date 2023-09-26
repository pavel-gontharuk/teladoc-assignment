package com.gontharuk.teladocassignment.twistandshout.data.entity

import org.json.JSONObject

class CollectionServerModelFactory {

    fun create(json: JSONObject): CollectionServerModel = CollectionServerModel(
        wrapperType = json.optString("wrapperType"),
        artistId = json.optInt("artistId"),
        collectionId = json.optInt("collectionId"),
        artistName = json.optString("artistName"),
        collectionName = json.optString("collectionName"),
        collectionCensoredName = json.optString("collectionCensoredName"),
        artistViewUrl = json.optString("artistViewUrl"),
        collectionViewUrl = json.optString("collectionViewUrl"),
        artworkUrl60 = json.optString("artworkUrl60"),
        artworkUrl100 = json.optString("artworkUrl100"),
        collectionPrice = json.optDouble("collectionPrice"),
        collectionExplicitness = json.optString("collectionExplicitness"),
        trackCount = json.optInt("trackCount"),
        country = json.optString("country"),
        currency = json.optString("currency"),
        releaseDate = json.optString("releaseDate"),
        primaryGenreName = json.optString("primaryGenreName"),
        previewUrl = json.optString("previewUrl"),
        description = json.optString("description"),
    )
}