package com.bankapp.home.feature.main.domain

sealed interface FetchingState {
    object Idle: FetchingState
    object Loading: FetchingState
    class Error(val resId: Int): FetchingState
}
