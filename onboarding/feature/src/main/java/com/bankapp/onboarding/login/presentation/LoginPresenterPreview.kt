package com.bankapp.onboarding.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class LoginPresenterPreview: LoginPresenter {
    override val email: State<String> = mutableStateOf("some_email@bank.com")
    override val emailError: State<Int?> = mutableStateOf(null)
    override val password: State<String> = mutableStateOf("12345678")
    override val passwordError: State<Int?> = mutableStateOf(null)

    override fun onEmailChanged(string: String) = Unit
    override fun onPasswordChanged(string: String) = Unit
    override fun loginClicked() = Unit
    override fun registerClicked() = Unit
}
