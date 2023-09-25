package com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity

import com.gontharuk.teladocassignment.core.mapper.JsonMapper
import org.json.JSONObject

class WordsCountItemMapper : JsonMapper<WordsCountItem> {

    override fun map(data: WordsCountItem): JSONObject = JSONObject().apply {
        put("word", data.word)
        put("count", data.count)
    }

    override fun map(json: JSONObject): WordsCountItem = WordsCountItem(
        word = json.optString("word", "__NO_DATA__"),
        count = json.optInt("count", -1),
    )
}