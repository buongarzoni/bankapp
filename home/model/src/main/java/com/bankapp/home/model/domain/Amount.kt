package com.bankapp.home.model.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success

@JvmInline
value class Amount private constructor(
    private val string: String,
) {
    val currency get() = string.split(DELIMITER)[0]
    val quantity get() = string.split(DELIMITER)[1].toDouble()

    companion object {
        val Zero = Amount("$ 0.0")

        fun valueOf(string: String): Either<AmountError, Amount> {
            if (string.isBlank()) return AmountError.INVALID_STRING.error()

            val (currency, amount) = string.split(DELIMITER)
            if (currency != "$") return AmountError.INVALID_STRING.error()

            try {
                amount.toDouble()
            } catch (numberFormatException: NumberFormatException) {
                return AmountError.INVALID_STRING.error()
            }

            return Amount(string).success()
        }

        private const val DELIMITER = " "
    }
}

enum class AmountError {
    INVALID_STRING
}
