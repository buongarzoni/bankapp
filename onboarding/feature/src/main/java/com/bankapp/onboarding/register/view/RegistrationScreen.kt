package com.bankapp.onboarding.register.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bankapp.components.modal.ErrorModal
import com.bankapp.components.modal.LoadingModal
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.onboarding.R
import com.bankapp.onboarding.register.domain.RegistrationState
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
private fun Content(
    modifier: Modifier,
    presenter: RegistrationPresenter,
) = Box(modifier = modifier) {
    StateModal(presenter)
    when (presenter.registrationView.value) {
        RegistrationView.PhotoIdView -> PhotoIdContent(presenter)
        RegistrationView.SuccessView -> SuccessContent(presenter)
        RegistrationView.UserDataView -> UserDataContent(presenter)
    }
}

@Composable
private fun StateModal(presenter: RegistrationPresenter) =
    when (val state = presenter.registrationState.value) {
        is RegistrationState.Error -> ErrorModal(
            onDismiss = { presenter.dismissErrorState() },
            onConfirm = { presenter.dismissErrorState() },
            text = stringResource(id = state.resId),
        )

        RegistrationState.Idle -> Unit
        RegistrationState.Loading -> LoadingModal(
            title = stringResource(id = R.string.onboarding_feature_headline_loading_registration),
        ) { /* can not dismiss */ }
    }
