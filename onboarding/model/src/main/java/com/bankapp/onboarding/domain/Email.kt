package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        fun valueOf(string: String): Either<Email, EmailError> {
            val lowercaseString = string.lowercase().trim()
            return if (lowercaseString.isValid()) {
                Email(lowercaseString).success()
            } else {
                EmailError.MALFORMED.error()
            }
        }

        private val emailRegex =
            ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()

        private fun String.isValid() = matches(emailRegex)
    }
}

enum class EmailError {
    MALFORMED
}
