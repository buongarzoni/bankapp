package com.bankapp.onboarding.register.presentation

import android.net.Uri
import androidx.compose.runtime.State
import com.bankapp.onboarding.register.domain.RegistrationState
import com.bankapp.onboarding.register.domain.RegistrationView

interface RegistrationPresenter {
    val registrationView: State<RegistrationView>
    val registrationState: State<RegistrationState>

    val name: State<String>
    val lastName: State<String>
    val email: State<String>
    val password: State<String>

    val nameError: State<Int?>
    val lastNameError: State<Int?>
    val emailError: State<Int?>
    val passwordError: State<Int?>

    val showImageSourceDialog: State<Boolean>
    val availableUri: State<Uri>
    val uri: State<Uri?>
    val uriError: State<Int?>

    fun onNameChange(string: String)
    fun onLastNameChange(string: String)
    fun onEmailChange(string: String)
    fun onPasswordChange(string: String)

    fun addPictureClicked()
    fun dismissImageSourceDialog()
    fun onUriLoaded(uri: Uri?)

    fun nextClicked()
    fun backClicked()
    fun submitClicked()

    fun dismissErrorState()
}
