package com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity

sealed class WordsCountUiState {

    abstract val filter: WordFiler

    data class Loading(
        override val filter: WordFiler = WordFiler.COUNT
    ) : WordsCountUiState()

    data class Show(
        override val filter: WordFiler = WordFiler.COUNT,
        val items: List<WordsCountItem> = emptyList()
    ) : WordsCountUiState()
}