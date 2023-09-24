package com.gontharuk.teladocassignment.romeoandjuliet.data

import com.gontharuk.teladocassignment.romeoandjuliet.domain.repository.WordsCountRepository
import java.util.regex.Pattern

class WordsCountRepositoryImpl(
    stringIterator: Iterator<String>,
    pattern: Pattern
) : WordsCountRepository {

    private val map: HashMap<String, Int> = HashMap()

    init {
        while (stringIterator.hasNext()) {
            stringIterator.next()
                .let { pattern.matcher(it) }
                .also { matcher ->
                    while (matcher.find()) {
                        put(matcher.group())
                    }
                }
        }
    }

    override fun put(vararg words: String) {
        words.forEach { next ->
            map.getOrPut(next) { 0 }
                .also { map[next] = it.inc() }
        }
    }

    override fun count(word: String): Int {
        return map.getOrDefault(word, -1)
    }

    override fun words(): Map<String, Int> {
        return map
    }
}