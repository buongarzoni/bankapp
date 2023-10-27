package com.bankapp.onboarding.utils

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.onboarding.R
import com.bankapp.onboarding.domain.Email
import com.bankapp.onboarding.domain.EmailError

fun emailFromInput(string: String): Either<Int, Email> {
    return when (val result = Email.valueOf(string)) {
        is Either.Error -> result.error.mapToResourceId().error()
        is Either.Success -> result.value.success()
    }
}

fun emailValidateInput(string: String): Int? {
    return when(val result = emailFromInput(string)) {
        is Either.Error -> result.error
        is Either.Success -> null
    }
}

private fun EmailError.mapToResourceId() = when (this) {
    EmailError.MALFORMED -> R.string.onboarding_feature_error_malformed_email
}
