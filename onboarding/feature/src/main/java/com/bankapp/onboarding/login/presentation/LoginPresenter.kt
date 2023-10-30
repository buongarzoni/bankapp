package com.bankapp.onboarding.login.presentation

import androidx.compose.runtime.State
import com.bankapp.onboarding.login.domain.LoginState

interface LoginPresenter {
    val loginState: State<LoginState>

    val email: State<String>
    val emailError: State<Int?>

    val password: State<String>
    val passwordError: State<Int?>

    fun onEmailChanged(string: String)
    fun onPasswordChanged(string: String)
    fun loginClicked()
    fun registerClicked()
    fun dismissError()
}
