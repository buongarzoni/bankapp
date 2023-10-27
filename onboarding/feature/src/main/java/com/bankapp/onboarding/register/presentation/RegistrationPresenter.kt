package com.bankapp.onboarding.register.presentation

import androidx.compose.runtime.State
import com.bankapp.onboarding.register.domain.RegistrationView

interface RegistrationPresenter {
    val registrationView: State<RegistrationView>

    val name: State<String>
    val lastName: State<String>
    val email: State<String>
    val password: State<String>

    val nameError: State<Int?>
    val lastNameError: State<Int?>
    val emailError: State<Int?>
    val passwordError: State<Int?>

    fun onNameChange(string: String)
    fun onLastNameChange(string: String)
    fun onEmailChange(string: String)
    fun onPasswordChange(string: String)

    fun nextClicked()
}
