package com.gontharuk.teladocassignment.romeoandjuliet.domain.usecase

import com.gontharuk.teladocassignment.romeoandjuliet.domain.repository.WordsCountRepository
import com.gontharuk.teladocassignment.romeoandjuliet.domain.usecase.WordsCountUseCase

class WordsCountUseCaseImpl(
    private val wordsCountRepository: WordsCountRepository
) : WordsCountUseCase {

    override fun words(): Map<String, Int> {
        return wordsCountRepository.words()
    }
}