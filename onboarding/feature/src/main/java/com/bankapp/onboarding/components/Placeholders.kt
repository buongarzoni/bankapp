package com.bankapp.onboarding.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bankapp.onboarding.R
import com.bankapp.onboarding.utils.AsTextFieldError

@Composable
fun EmailPlaceholder(
    value: String,
    error: Int?,
    onEmailChanged: (String) -> Unit,
) {
    EmailInput(value, onEmailChanged)
    EmailError(error)
}

@Composable
fun PasswordPlaceholder(
    value: String,
    error: Int?,
    onPasswordChange: (String) -> Unit,
) {
    PasswordInput(value, onPasswordChange)
    PasswordError(error)
}

@Composable
private fun PasswordInput(
    value: String,
    onPasswordChange: (String) -> Unit,
) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.onboarding_cd_hide_password)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.onboarding_cd_show_password)
                    )
                }
            }
        },
        label = { Text(stringResource(R.string.feature_onboarding_label_password)) },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        value = value,
        onValueChange = { onPasswordChange(it) },
    )
}

@Composable
private fun EmailInput(
    value: String,
    onEmailChange: (String) -> Unit,
) = OutlinedTextField(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
    label = { Text(stringResource(R.string.feature_onboarding_label_email)) },
    leadingIcon = {
        Icon(
            imageVector = Icons.Filled.Email,
            contentDescription = stringResource(R.string.onboarding_cd_email_placeholder),
        )
    },
    value = value,
    onValueChange = { onEmailChange(it) },
)

@Composable
private fun PasswordError(error: Int?) = error.AsTextFieldError()

@Composable
private fun EmailError(error: Int?) = error.AsTextFieldError()
