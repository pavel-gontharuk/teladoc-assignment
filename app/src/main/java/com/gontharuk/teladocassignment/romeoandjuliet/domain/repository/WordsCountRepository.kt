package com.gontharuk.teladocassignment.romeoandjuliet.domain.repository

interface WordsCountRepository {

    fun put(vararg words: String)

    fun count(word: String): Int

    fun words(): Map<String, Int>
}