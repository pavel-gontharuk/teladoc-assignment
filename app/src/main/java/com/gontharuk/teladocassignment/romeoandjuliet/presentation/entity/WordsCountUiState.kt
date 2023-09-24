package com.gontharuk.teladocassignment.romeoandjuliet.presentation.entity

sealed class WordsCountUiState {

    data object Loading : WordsCountUiState()

    data class Show(val items: List<WordsCountItem>) : WordsCountUiState()
}