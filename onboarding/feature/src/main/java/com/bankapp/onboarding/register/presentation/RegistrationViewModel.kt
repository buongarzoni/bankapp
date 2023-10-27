package com.bankapp.onboarding.register.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.onboarding.register.domain.RegistrationView
import com.bankapp.onboarding.utils.emailValidateInput
import com.bankapp.onboarding.utils.nameValidateInput
import com.bankapp.onboarding.utils.passwordValidateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(),
    RegistrationPresenter,
    RouteNavigator by routeNavigator {

    private val _registrationView = mutableStateOf<RegistrationView>(RegistrationView.UserDataView)
    override val registrationView: State<RegistrationView> = _registrationView

    private val _name = mutableStateOf("")
    override val name: State<String> = _name

    private val _lastName = mutableStateOf("")
    override val lastName: State<String> = _lastName

    private val _email = mutableStateOf("")
    override val email: State<String> = _email

    private val _password = mutableStateOf("")
    override val password: State<String> = _password

    private val _nameError = mutableStateOf<Int?>(null)
    override val nameError: State<Int?> = _nameError

    private val _lastNameError = mutableStateOf<Int?>(null)
    override val lastNameError: State<Int?> = _lastNameError

    private val _emailError = mutableStateOf<Int?>(null)
    override val emailError: State<Int?> = _emailError

    private val _passwordError = mutableStateOf<Int?>(null)
    override val passwordError: State<Int?> = _passwordError

    override fun onNameChange(string: String) {
        _name.value = string
        nameValidation()
    }

    override fun onLastNameChange(string: String) {
        _lastName.value = string
        lastNameValidation()
    }

    override fun onEmailChange(string: String) {
        _email.value = string
        emailValidation()
    }

    override fun onPasswordChange(string: String) {
        _password.value = string
        passwordValidation()
    }

    override fun nextClicked() {
        executeUserDataValidations()
        if (userDataIsValid()) {
            _registrationView.value = RegistrationView.PhotoIdView
        }
    }

    private fun userDataIsValid() = (_nameError.value == null && _lastNameError.value == null && _emailError.value == null && _passwordError.value == null
            )

    private fun executeUserDataValidations() {
        nameValidation()
        lastNameValidation()
        emailValidation()
        passwordValidation()
    }

    private fun nameValidation() {
        _nameError.value = nameValidateInput(_name.value)
    }

    private fun lastNameValidation() {
        _lastNameError.value = nameValidateInput(_lastName.value)
    }

    private fun emailValidation() {
        _emailError.value = emailValidateInput(_email.value)
    }

    private fun passwordValidation() {
        _passwordError.value = passwordValidateInput(_password.value)
    }
}
