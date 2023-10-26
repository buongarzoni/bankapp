package com.bankapp.onboarding.register.presentation

import androidx.compose.runtime.mutableStateOf
import com.bankapp.onboarding.register.domain.RegistrationView

class RegistrationPreview : RegistrationPresenter {
    override val registrationView = mutableStateOf<RegistrationView>(RegistrationView.UserDataView)
}
