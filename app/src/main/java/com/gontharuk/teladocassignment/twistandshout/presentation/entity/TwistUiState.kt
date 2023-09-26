package com.gontharuk.teladocassignment.twistandshout.presentation.entity

sealed class TwistUiState {

    data class Loading(
        val search: String = ""
    ) : TwistUiState()

    data class Show(
        val search: String = "",
        val items: List<TwistItem> = emptyList()
    ) : TwistUiState()
}
