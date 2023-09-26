package com.gontharuk.teladocassignment.twistandshout.presentation.entity

import com.gontharuk.teladocassignment.twistandshout.data.entity.CollectionServerModel

data class TwistItem(
    val albumName: String
) {
    constructor(serverModel: CollectionServerModel) : this(
        albumName = serverModel.collectionName
    )
}
