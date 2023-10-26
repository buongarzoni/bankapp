package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success

@JvmInline
value class Password private constructor(
    val value: String
) {
    companion object {
        fun valueOf(string: String): Either<PasswordError, Password> {
            if (string.isTooShort()) return PasswordError.SHORT.error()
            if (string.isTooLong()) return PasswordError.LONG.error()
            if (string.containsInvalidCharacters()) return PasswordError.INVALID_CHARACTER.error()
            return Password(string).success()
        }

        private const val MIN_CHARACTERS = 8
        private const val MAX_CHARACTERS = 32
        private val regex = """^[0-9a-zA-ZñÑ_-]*${'$'}""".toRegex()
        private fun String.containsInvalidCharacters() = !matches(regex)
        private fun String.isTooShort() = length < MIN_CHARACTERS
        private fun String.isTooLong() = length > MAX_CHARACTERS
    }
}

enum class PasswordError {
    SHORT,
    LONG,
    INVALID_CHARACTER
}
