package com.bankapp.onboarding.actions

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.user.UserId
import com.bankapp.onboarding.domain.Auth
import com.bankapp.onboarding.domain.RegistrationData
import com.bankapp.onboarding.domain.Users
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bankapp.core.auth.Auth as AuthCore

class RegisterUser(
    private val auth: Auth,
    private val authCore: AuthCore,
    private val users: Users,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun execute(
        registrationData: RegistrationData,
    ): Either<Int, Unit> = withContext(dispatcher) {

        when (val authResult = authNewAccount(registrationData)) {
            is Either.Error -> authResult
            is Either.Success -> {
                when (val loggedUserId = getLoggedUserId()) {
                    is Either.Error -> loggedUserId.error.error()
                    is Either.Success -> {
                        register(loggedUserId.value, registrationData)
                    }
                }
            }
        }
    }

    private suspend fun authNewAccount(registrationData: RegistrationData) =
        auth.createUserWithEmailAndPassword(registrationData.email, registrationData.password)

    private fun getLoggedUserId() = authCore.getLoggedUserId()

    private suspend fun register(userId: UserId, registrationData: RegistrationData) =
        users.register(userId, registrationData)
}
