package com.gontharuk.teladocassignment.twistandshout.data.entity

data class CollectionServerModel(
    val wrapperType: String,
    val artistId: Int,
    val collectionId: Int,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: String,
    val trackCount: Int,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String,
    val previewUrl: String,
    val description: String,
)