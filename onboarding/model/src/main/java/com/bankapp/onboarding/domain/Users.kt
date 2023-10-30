package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.user.UserId

interface Users {
    suspend fun register(
        userId: UserId,
        registrationData: RegistrationData,
    ): Either<Int, Unit>
}
