package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either

interface Auth {
    suspend fun createUserWithEmailAndPassword(
        email: Email,
        password: Password,
    ): Either<Int, Unit>
}
