package com.bankapp.onboarding.register.domain

sealed interface RegistrationState {
    object Idle: RegistrationState
    object Loading: RegistrationState
    class Error(val resId: Int): RegistrationState
}
