package com.bankapp.onboarding.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bankapp.components.text.TextFieldError

@Composable
fun Int?.AsTextFieldError() = this?.let { TextFieldError(text = stringResource(id = it)) }
