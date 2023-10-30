package com.bankapp.onboarding.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.core.domain.Either
import com.bankapp.core.domain.asSuccess
import com.bankapp.onboarding.actions.Login
import com.bankapp.onboarding.domain.Email
import com.bankapp.onboarding.domain.Password
import com.bankapp.onboarding.login.domain.LoginState
import com.bankapp.onboarding.utils.emailValidateInput
import com.bankapp.onboarding.utils.passwordValidateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val login: Login,
    private val navigateOnSuccessLogin: () -> Unit,
    private val navigateToRegistration: () -> Unit,
) : ViewModel(),
    LoginPresenter,
    RouteNavigator by routeNavigator {
    private val _loginState = mutableStateOf<LoginState>(LoginState.Idle)
    override val loginState: State<LoginState> = _loginState

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
        validateEmail()
    }

    override fun onPasswordChanged(string: String) {
        _password.value = string
        validatePassword()
    }

    override fun loginClicked() {
        executeAllValidations()
        if (credentialsAreValid()) {
            _loginState.value = LoginState.Loading
            viewModelScope.launch {
                when(val loginResult = executeLogin()) {
                    is Either.Error -> _loginState.value = LoginState.Error(loginResult.error)
                    is Either.Success -> navigateOnSuccessLogin()
                }
            }
        }
    }

    override fun registerClicked() {
        navigateToRegistration()
    }

    override fun dismissError() {
        _loginState.value = LoginState.Idle
    }

    private suspend fun executeLogin() = login.execute(
        email = Email.valueOf(_email.value).asSuccess(),
        password = Password.valueOf(_password.value).asSuccess(),
    )

    private fun executeAllValidations() {
        validateEmail()
        validatePassword()
    }

    private fun credentialsAreValid() = _emailError.value == null && _passwordError.value == null

    private fun validateEmail() {
        _emailError.value = emailValidateInput(_email.value)
    }

    private fun validatePassword() {
        _passwordError.value = passwordValidateInput(_password.value)
    }
}
