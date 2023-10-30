package com.bankapp.onboarding.actions

import com.bankapp.core.domain.Either
import com.bankapp.onboarding.domain.Auth
import com.bankapp.onboarding.domain.Email
import com.bankapp.onboarding.domain.Password
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Login (
    private val auth: Auth,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
){
    suspend fun execute(
        email: Email,
        password: Password,
    ): Either<Int, Unit> = withContext(dispatcher) {
        auth.login(email, password)
    }
}
