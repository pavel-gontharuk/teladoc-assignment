package com.gontharuk.teladocassignment.twistandshout.presentation.presenter

import com.gontharuk.teladocassignment.twistandshout.data.api.TwistRepositoryImpl
import com.gontharuk.teladocassignment.twistandshout.data.rest.ConnectionService
import com.gontharuk.teladocassignment.twistandshout.presentation.entity.TwistUiState

class TwistPresenterFactory {

    fun create(
        initState: TwistUiState,
        search: String?
    ): TwistPresenter {
        val service = ConnectionService(
            baseUrl = "https://itunes.apple.com",
            readTimeout = 10_000,
            connectTimeout = 10_000,
        )
        val twistRepository = TwistRepositoryImpl(service)
        val state = if (initState is TwistUiState.Loading && search != null) {
            TwistUiState.Loading(search = search)
        } else initState
        return TwistPresenter(
            initState = state,
            twistRepository = twistRepository
        )
    }
}