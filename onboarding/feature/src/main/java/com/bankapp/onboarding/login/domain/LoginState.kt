package com.bankapp.onboarding.login.domain

sealed interface LoginState {
    object Idle: LoginState
    object Loading: LoginState
    class Error(val resId: Int): LoginState
}
