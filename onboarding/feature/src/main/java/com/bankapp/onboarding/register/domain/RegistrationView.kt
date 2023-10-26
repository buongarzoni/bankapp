package com.bankapp.onboarding.register.domain

sealed interface RegistrationView {
    object UserDataView : RegistrationView
    object PhotoIdView : RegistrationView
    object SuccessView : RegistrationView
}
