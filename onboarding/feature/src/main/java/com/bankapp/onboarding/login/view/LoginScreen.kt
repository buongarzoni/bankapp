package com.bankapp.onboarding.login.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bankapp.components.modal.ErrorModal
import com.bankapp.components.modal.LoadingModal
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R
import com.bankapp.onboarding.components.EmailPlaceholder
import com.bankapp.onboarding.components.PasswordPlaceholder
import com.bankapp.onboarding.login.domain.LoginState
import com.bankapp.onboarding.login.presentation.LoginPresenter
import com.bankapp.onboarding.login.presentation.LoginPresenterPreview

@Composable
fun LoginScreen(
    presenter: LoginPresenter,
) = Scaffold(
    topBar = { BankTopAppBar() },
) {
    Content(Modifier.padding(it), presenter)
}

@Composable
private fun Content(
    modifier: Modifier,
    presenter: LoginPresenter,
) = Box(modifier = modifier) {
    LoginStateHandler(presenter)
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Lottie()
            Spacer(modifier = Modifier.height(60.dp))
            Email(presenter)
            Password(presenter)
            Spacer(modifier = Modifier.height(40.dp))
            LoginButton(presenter)
            RegisterButton(presenter)
        }
    }
}

@Composable
private fun Lottie() {
    val composition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_onboarding_login))
    LottieAnimation(
        modifier = Modifier.size(200.dp),
        composition = composition.value,
    )
}
@Composable
private fun LoginStateHandler(presenter: LoginPresenter) {
    when(val state = presenter.loginState.value) {
        is LoginState.Error -> ErrorModal(
            onDismiss = { presenter.dismissError() },
            onConfirm = { presenter.dismissError() },
            text = stringResource(id = state.resId),
        )
        LoginState.Idle -> Unit
        LoginState.Loading -> LoadingModal(
            title = stringResource(id = R.string.onboarding_feature_headline_loading_registration),
        ) { /* can not dismiss */ }
    }
}

@Composable
private fun Email(presenter: LoginPresenter) = EmailPlaceholder(
    value = presenter.email.value,
    error = presenter.emailError.value,
    onEmailChanged = { presenter.onEmailChanged(it) },
)

@Composable
private fun Password(presenter: LoginPresenter) = PasswordPlaceholder(
    value = presenter.password.value,
    error = presenter.passwordError.value,
    onPasswordChange = { presenter.onPasswordChanged(it) },
)

@Composable
private fun LoginButton(presenter: LoginPresenter) = Button(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp),
    onClick = { presenter.loginClicked() },
) {
    Text(stringResource(R.string.onboarding_feature_button_login))
}

@Composable
private fun RegisterButton(presenter: LoginPresenter) = OutlinedButton(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    onClick = { presenter.registerClicked() }
) {
    Text(stringResource(R.string.onboarding_feature_button_register))
}

@UIModePreview
@Composable
fun Preview() = BankappTheme(dynamicColor = false) {
    LoginScreen(LoginPresenterPreview())
}
