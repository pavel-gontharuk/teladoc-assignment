package com.gontharuk.teladocassignment.twistandshout.presentation.entity

sealed class TwistUiState {

    abstract val search: String

    data class Loading(
        override val search: String = ""
    ) : TwistUiState()

    data class Show(
        override val search: String = "",
        val items: List<TwistItem> = emptyList()
    ) : TwistUiState()
}
