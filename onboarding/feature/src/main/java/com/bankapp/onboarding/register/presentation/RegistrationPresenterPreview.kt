package com.bankapp.onboarding.register.presentation

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.bankapp.onboarding.register.domain.RegistrationState
import com.bankapp.onboarding.register.domain.RegistrationView

class RegistrationPresenterPreview : RegistrationPresenter {
    override val registrationView = mutableStateOf<RegistrationView>(RegistrationView.UserDataView)
    override val registrationState: State<RegistrationState> = mutableStateOf(RegistrationState.Idle)

    override val name: State<String> = mutableStateOf("Christian")
    override val lastName: State<String> = mutableStateOf("Buongarzoni")
    override val email: State<String> = mutableStateOf("some_email@bankapp.com")
    override val password: State<String> = mutableStateOf("12345678")

    override val nameError: State<Int?> = mutableStateOf(null)
    override val lastNameError: State<Int?> = mutableStateOf(null)
    override val emailError: State<Int?> = mutableStateOf(null)
    override val passwordError: State<Int?> = mutableStateOf(null)

    override val showImageSourceDialog: State<Boolean> = mutableStateOf(false)
    override val availableUri: State<Uri> = mutableStateOf(Uri.EMPTY)
    override val uri: State<Uri?> = mutableStateOf(null)
    override val uriError: State<Int?> = mutableStateOf(null)

    override fun onNameChange(string: String) = Unit
    override fun onLastNameChange(string: String) = Unit
    override fun onEmailChange(string: String) = Unit
    override fun onPasswordChange(string: String) = Unit

    override fun addPictureClicked() = Unit
    override fun dismissImageSourceDialog() = Unit
    override fun onUriLoaded(uri: Uri?) = Unit

    override fun nextClicked() = Unit
    override fun backClicked() = Unit
    override fun submitClicked() = Unit

    override fun dismissErrorState() = Unit
}
