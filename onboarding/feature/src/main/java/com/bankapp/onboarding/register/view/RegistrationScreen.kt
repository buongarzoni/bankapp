package com.bankapp.onboarding.register.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.onboarding.register.domain.RegistrationView
import com.bankapp.onboarding.register.presentation.RegistrationPresenter

@Composable
fun RegistrationScreen(presenter: RegistrationPresenter) = Scaffold(
    topBar = { BankTopAppBar() },
) {
    Content(
        modifier = Modifier.padding(it),
        presenter = presenter,
    )
}

@Composable
fun Content(
    modifier: Modifier,
    presenter: RegistrationPresenter,
) = Box(modifier = modifier) {
    when (presenter.registrationView.value) {
        RegistrationView.PhotoIdView -> PhotoIdContent(presenter)
        RegistrationView.SuccessView -> SuccessContent(presenter)
        RegistrationView.UserDataView -> UserDataContent(presenter)
    }
}
