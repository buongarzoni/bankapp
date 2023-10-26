package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success

@JvmInline
value class Name private constructor(
    val value: String,
) {
    companion object {
        fun valueOf(string: String): Either<Name, NameError> {
            val trimmedString = string.trim()
            if (trimmedString.isTooShort()) return NameError.SHORT.error()
            if (trimmedString.isTooLong()) return NameError.LONG.error()
            if (trimmedString.containsInvalidCharacters()) return NameError.INVALID_CHARACTER.error()
            return Name(trimmedString).success()
        }

        private const val MIN_CHARACTERS = 2
        private const val MAX_CHARACTERS = 20
        private val regex = """^[a-zA-Z ñÑ]*${'$'}""".toRegex()
        private fun String.containsInvalidCharacters() = !matches(regex)
        private fun String.isTooShort() = length < MIN_CHARACTERS
        private fun String.isTooLong() = length > MAX_CHARACTERS
    }
}

enum class NameError {
    LONG,
    SHORT,
    INVALID_CHARACTER
}
