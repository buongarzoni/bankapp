package com.bankapp.onboarding.actions

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.asError
import com.bankapp.core.domain.asSuccess
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.core.user.UserId
import com.bankapp.onboarding.domain.Auth
import com.bankapp.onboarding.domain.RegistrationData
import com.bankapp.onboarding.domain.Users
import com.bankapp.onboarding.domain.emailDummy
import com.bankapp.onboarding.domain.nameDummy
import com.bankapp.onboarding.domain.passwordDummy
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import com.bankapp.core.auth.Auth as AuthCore

class RegisterUserMust {
    private val registrationDataDummy = RegistrationData(
        email = emailDummy(),
        password = passwordDummy(),
        name = nameDummy(),
        lastName = nameDummy(),
        byteArray = ByteArray(0),
    )

    private val userIdDummy = UserId.valueOf("1").asSuccess()
    private val users: Users = mockk()
    private val auth: Auth = mockk()
    private val authCore: AuthCore = mockk()
    private val dispatcher = StandardTestDispatcher()

    private val registerUser = RegisterUser(
        auth = auth,
        authCore = authCore,
        users = users,
        dispatcher = dispatcher,
    )

    @Before
    fun setUp() {
        runBlocking {
            `when create account auth result`()
            `when get logged user`()
            `when persist user data`()
        }
    }

    @Test
    fun `create an account with auth provider and save user data`() = runTest(dispatcher) {
        val result = registerUser.execute(registrationDataDummy)

        assertTrue(result is Either.Success)
        `then create new account with auth must be called`()
        `then get logged user id must be called`(1)
        `then register must be called`(1)
    }

    @Test
    fun `return an error if save new user data fails`() = runTest(dispatcher) {
        val usersError = 1.error<Int, Unit>()
        `when persist user data`(usersError)

        val result = registerUser.execute(registrationDataDummy)

        assertEquals(usersError.error, result.asError())
        `then create new account with auth must be called`()
        `then get logged user id must be called`(1)
        `then register must be called`(1)
    }

    @Test
    fun `return an error if no logged user is found`() = runTest(dispatcher) {
        val authCoreError = 1.error<Int, UserId>()
        `when get logged user`(authCoreError)

        val result = registerUser.execute(registrationDataDummy)

        assertEquals(authCoreError.error, result.asError())
        `then create new account with auth must be called`()
        `then get logged user id must be called`(1)
        `then register must be called`(0)
    }

    @Test
    fun `return an error if account can not be created by auth provider`() = runTest(dispatcher) {
        val authError = 1.error<Int, Unit>()
        `when create account auth result`(authError)

        val result = registerUser.execute(registrationDataDummy)

        assertEquals(authError.error, result.asError())
        `then create new account with auth must be called`()
        `then get logged user id must be called`(0)
        `then register must be called`(0)
    }

    private suspend fun `when create account auth result`(
        result: Either<Int, Unit> = Unit.success(),
    ) {
        coEvery {
            auth.createUserWithEmailAndPassword(
                registrationDataDummy.email,
                registrationDataDummy.password
            )
        } returns result
    }

    private fun `when get logged user`(
        result: Either<Int, UserId> = userIdDummy.success(),
    ) {
        every { authCore.getLoggedUserId() } returns result
    }

    private suspend fun `when persist user data`(
        result: Either<Int, Unit> = Unit.success(),
    ) {
        coEvery { users.register(userIdDummy, registrationDataDummy) } returns result
    }

    private fun `then create new account with auth must be called`() {
        coVerify {
            auth.createUserWithEmailAndPassword(
                registrationDataDummy.email,
                registrationDataDummy.password
            )
        }
    }

    private fun `then get logged user id must be called`(times: Int = 1) {
        verify(exactly = times) { authCore.getLoggedUserId() }
    }

    private fun `then register must be called`(times: Int = 1) {
        coVerify(exactly = times) { users.register(userIdDummy, registrationDataDummy) }
    }
}
