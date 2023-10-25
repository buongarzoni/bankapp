package com.bankapp.onboarding.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class LoginViewModel: LoginPresenter {
    private val _email = mutableStateOf("")
    override val email: State<String> = _email

    private val _emailError = mutableStateOf<Int?>(null)
    override val emailError: State<Int?> = _emailError

    private val _password = mutableStateOf("")
    override val password: State<String> = _password

    private val _passwordError = mutableStateOf<Int?>(null)
    override val passwordError: State<Int?> = _passwordError

    override fun onEmailChanged(string: String) {
        _email.value = string
    }

    override fun onPasswordChanged(string: String) {
        _password.value = string
    }

    override fun loginClicked() {
        TODO("Not yet implemented")
    }

    override fun registerClicked() {
        TODO("Not yet implemented")
    }
}
