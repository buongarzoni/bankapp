package com.bankapp.core.user

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success

@JvmInline
value class UserId private constructor(
    val id: String,
) {
    companion object {
        fun valueOf(string: String): Either<UserIdError, UserId> {
            return if (string.isBlank()) UserIdError.EMPTY.error() else UserId(string).success()
        }
    }

    enum class UserIdError {
        EMPTY
    }
}
