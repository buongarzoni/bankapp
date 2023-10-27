package com.bankapp.onboarding.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R
import com.bankapp.onboarding.components.EmailPlaceholder
import com.bankapp.onboarding.components.PasswordPlaceholder
import com.bankapp.onboarding.register.presentation.RegistrationPresenter
import com.bankapp.onboarding.register.presentation.RegistrationPresenterPreview
import com.bankapp.onboarding.utils.AsTextFieldError

@Composable
fun UserDataContent(presenter: RegistrationPresenter) = LazyColumn(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
    horizontalAlignment = Alignment.End,
    verticalArrangement = Arrangement.Bottom,
) {
    item {
        Name(presenter)
        Spacer(Modifier.height(8.dp))
        LastName(presenter)
        Spacer(Modifier.height(8.dp))
        Email(presenter)
        Spacer(Modifier.height(8.dp))
        Password(presenter)
        Spacer(Modifier.height(24.dp))
        NextButton(presenter)
    }
}

@Composable
private fun Name(presenter: RegistrationPresenter) {
    NameInput(presenter)
    NameError(presenter)
}

@Composable
private fun LastName(presenter: RegistrationPresenter) {
    LastNameInput(presenter)
    LastNameError(presenter)
}

@Composable
private fun Email(presenter: RegistrationPresenter) = EmailPlaceholder(
    value = presenter.email.value,
    error = presenter.emailError.value,
    onEmailChanged = { presenter.onEmailChange(it) },
)

@Composable
private fun Password(presenter: RegistrationPresenter) = PasswordPlaceholder(
    value = presenter.password.value,
    error = presenter.passwordError.value,
    onPasswordChange = { presenter.onPasswordChange(it) },
)

@Composable
private fun NextButton(presenter: RegistrationPresenter) = Button(
    modifier = Modifier.fillMaxWidth(0.4f),
    onClick = { presenter.nextClicked() },
) {
    Text(stringResource(R.string.onboarding_feature_button_next))
}

@Composable
private fun NameInput(presenter: RegistrationPresenter) = OutlinedTextField(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    label = { Text(stringResource(R.string.onboarding_feature_label_name)) },
    leadingIcon = {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.onboarding_feature_cd_name_placeholder),
        )
    },
    value = presenter.name.value,
    onValueChange = { presenter.onNameChange(it) },
)

@Composable
private fun LastNameInput(presenter: RegistrationPresenter) = OutlinedTextField(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    label = { Text(stringResource(R.string.onboarding_feature_label_last_name)) },
    leadingIcon = {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.onboarding_feature_cd_last_name_placeholder),
        )
    },
    value = presenter.lastName.value,
    onValueChange = { presenter.onLastNameChange(it) },
)

@Composable
private fun NameError(presenter: RegistrationPresenter) = presenter.nameError.value.AsTextFieldError()

@Composable
private fun LastNameError(presenter: RegistrationPresenter) = presenter.lastNameError.value.AsTextFieldError()

@UIModePreview
@Composable
private fun Preview() = BankappTheme(dynamicColor = false) {
    UserDataContent(RegistrationPresenterPreview())
}
