package com.bankapp.onboarding.register.presentation

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.onboarding.R
import com.bankapp.onboarding.register.domain.RegistrationState
import com.bankapp.onboarding.register.domain.RegistrationView
import com.bankapp.onboarding.utils.emailValidateInput
import com.bankapp.onboarding.utils.nameValidateInput
import com.bankapp.onboarding.utils.passwordValidateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    getNewUri: () -> Uri,
) : ViewModel(),
    RegistrationPresenter,
    RouteNavigator by routeNavigator {

    private val _registrationView = mutableStateOf<RegistrationView>(RegistrationView.UserDataView)
    override val registrationView: State<RegistrationView> = _registrationView

    private val _registrationState = mutableStateOf<RegistrationState>(RegistrationState.Idle)
    override val registrationState: State<RegistrationState> = _registrationState

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

    private val _showImageSourceDialog = mutableStateOf(false)
    override val showImageSourceDialog: State<Boolean> = _showImageSourceDialog

    private val _availableUri = mutableStateOf(Uri.EMPTY)
    override val availableUri: State<Uri> = _availableUri

    private val _uri = mutableStateOf<Uri?>(null)
    override val uri: State<Uri?> = _uri

    private val _uriError = mutableStateOf<Int?>(null)
    override val uriError: State<Int?> = _uriError

    init {
        _availableUri.value = getNewUri()
    }

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

    override fun addPictureClicked() {
        _showImageSourceDialog.value = true
    }

    override fun dismissImageSourceDialog() {
        _showImageSourceDialog.value = false
    }

    override fun onUriLoaded(uri: Uri?) {
        if (uri != null) {
            _uriError.value = null
            _uri.value = uri
            _showImageSourceDialog.value = false
        } else {
            _uriError.value = R.string.onboarding_feature_error_picture_id
        }
    }

    override fun nextClicked() {
        executeUserDataValidations()
        if (userDataIsValid()) {
            _registrationView.value = RegistrationView.PhotoIdView
        }
    }

    override fun backClicked() {
        _registrationView.value = RegistrationView.UserDataView
    }

    override fun submitClicked() {
        _registrationState.value = RegistrationState.Loading
        executeAllValidations()
        if (isSubmissionComplete()) {
            //TODO call action
        }
        _registrationState.value = RegistrationState.Idle
    }

    override fun dismissErrorState() {
        _registrationState.value = RegistrationState.Idle
    }

    private fun isSubmissionComplete() = userDataIsValid() && _uriError.value == null

    private fun userDataIsValid() =
        (_nameError.value == null && _lastNameError.value == null
                && _emailError.value == null && _passwordError.value == null)

    private fun executeAllValidations() {
        executeUserDataValidations()
        imageValidation()
    }

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

    private fun imageValidation() {
        //val uri = _uri.value TODO
    }

}
