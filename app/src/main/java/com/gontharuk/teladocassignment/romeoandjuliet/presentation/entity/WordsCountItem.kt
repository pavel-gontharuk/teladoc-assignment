package com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity

data class WordsCountItem(
    val word: String,
    val count: Int
) {

    constructor(entry: Map.Entry<String, Int>) : this(
        word = entry.key,
        count = entry.value,
    )
}
