package com.bankapp.onboarding.register.presentation

import androidx.compose.runtime.State
import com.bankapp.onboarding.register.domain.RegistrationView

interface RegistrationPresenter {
    val registrationView: State<RegistrationView>
}
