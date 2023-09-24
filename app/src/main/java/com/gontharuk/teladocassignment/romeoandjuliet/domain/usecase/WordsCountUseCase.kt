package com.gontharuk.teladocassignment.romeoandjuliet.domain.usecase

interface WordsCountUseCase {

    fun words(): Map<String, Int>
}