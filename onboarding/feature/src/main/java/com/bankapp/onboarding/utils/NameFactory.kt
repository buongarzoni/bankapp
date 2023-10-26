package com.bankapp.onboarding.utils

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.onboarding.R
import com.bankapp.onboarding.domain.Name
import com.bankapp.onboarding.domain.NameError

fun nameFromInput(string: String): Either<Int, Name> {
    return when (val result = Name.valueOf(string)) {
        is Either.Error -> result.error.mapToResourceId().error()
        is Either.Success -> result.value.success()
    }
}

fun nameValidateInput(string: String): Int? {
    return when(val result = nameFromInput(string)) {
        is Either.Error -> result.error
        is Either.Success -> null
    }
}

private fun NameError.mapToResourceId() = when (this) {
    NameError.LONG -> R.string.onboarding_error_name_long
    NameError.SHORT -> R.string.onboarding_error_name_short
    NameError.INVALID_CHARACTER -> R.string.onboarding_error_name_invalid_character
}
