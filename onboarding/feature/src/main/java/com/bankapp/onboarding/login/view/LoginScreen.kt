package com.bankapp.onboarding.login.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.screen.BankTopAppBar
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R
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
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            EmailStateHolder(presenter)
            PasswordStateHolder(presenter)
            Spacer(modifier = Modifier.height(20.dp))
            LoginButton(presenter)
            RegisterButton(presenter)
        }
    }
}

@Composable
private fun EmailStateHolder(presenter: LoginPresenter) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        label = { Text(stringResource(R.string.feature_onboarding_label_email)) },
        value = presenter.email.value,
        onValueChange = { presenter.onEmailChanged(it) },
    )
}

@Composable
private fun PasswordStateHolder(presenter: LoginPresenter) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.feature_onboarding_label_password)) },
        value = presenter.password.value,
        onValueChange = { presenter.onPasswordChanged(it) },
    )
}

@Composable
private fun LoginButton(presenter: LoginPresenter) = Button(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    onClick = { presenter.loginClicked() },
) {
    Text(stringResource(R.string.feature_onboarding_button_login))
}

@Composable
private fun RegisterButton(presenter: LoginPresenter) = OutlinedButton(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    onClick = { presenter.registerClicked() }
) {
    Text(stringResource(R.string.feature_onboarding_button_register))
}

@UIModePreview
@Composable
fun Preview() = BankappTheme(dynamicColor = false) {
    LoginScreen(LoginPresenterPreview())
}