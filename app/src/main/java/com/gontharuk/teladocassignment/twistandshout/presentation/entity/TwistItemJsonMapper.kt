package com.gontharuk.teladocassignment.twistandshout.presentation.entity

import com.gontharuk.teladocassignment.core.mapper.JsonMapper
import org.json.JSONObject

class TwistItemJsonMapper : JsonMapper<TwistItem> {

    override fun map(data: TwistItem): JSONObject = JSONObject().apply {
        put("albumName", data.albumName)
    }

    override fun map(json: JSONObject): TwistItem = TwistItem(
        albumName = json.optString("albumName")
    )
}