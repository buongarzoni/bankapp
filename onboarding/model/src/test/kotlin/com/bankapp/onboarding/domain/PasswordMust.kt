package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.asError
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordMust {

    private val validPassword = "0123456789AanÑ-_"

    @Test
    fun `return Long error when Password contains more than 32 characters`() {
        val longPasswordError = Password.valueOf("123456789012345678901234567890123").asError()

        Assert.assertEquals(PasswordError.LONG, longPasswordError)
    }

    @Test
    fun `return Short error when Password contains less than 8 characters`() {
        val shortPasswordError = Password.valueOf("1234567").asError()

        Assert.assertEquals(PasswordError.SHORT, shortPasswordError)
    }

    @Test
    fun `return Invalid Character error when Password contains an unexpected character`() {
        val invalidCharactersError = Password.valueOf("$validPassword ").asError()

        Assert.assertEquals(PasswordError.INVALID_CHARACTER, invalidCharactersError)
    }

    @Test
    fun `be valid for expected characters and length`() {
        val password = Password.valueOf(validPassword)

        assertTrue(password is Either.Success)
    }

}
