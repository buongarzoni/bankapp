package com.bankapp.onboarding.utils

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.onboarding.R
import com.bankapp.onboarding.domain.Password
import com.bankapp.onboarding.domain.PasswordError

fun passwordFromInput(string: String): Either<Int, Password> {
    return when (val result = Password.valueOf(string)) {
        is Either.Error -> result.error.mapToResourceId().error()
        is Either.Success -> result.value.success()
    }
}

fun passwordValidateInput(string: String): Int? {
    return when(val result = passwordFromInput(string)) {
        is Either.Error -> result.error
        is Either.Success -> null
    }
}

private fun PasswordError.mapToResourceId() = when (this) {
    PasswordError.LONG -> R.string.onboarding_error_password_long
    PasswordError.SHORT -> R.string.onboarding_error_password_short
    PasswordError.INVALID_CHARACTER -> R.string.onboarding_error_name_password_character
}
