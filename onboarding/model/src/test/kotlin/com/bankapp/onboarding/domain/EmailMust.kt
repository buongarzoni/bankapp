package com.bankapp.onboarding.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.asError
import com.bankapp.core.domain.asSuccess
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class EmailMust {

    @Test
    fun `return malformed error when a String is not compliant with email structure`() {
        val invalidEmail = Email.valueOf("some random string")

        assertTrue(invalidEmail is Either.Error)
        assertEquals(EmailError.MALFORMED, invalidEmail.asError().error)
    }

    @Test
    fun `return malformed error for an invalid character`() {
        val invalidEmail = Email.valueOf("some_email!@bankapp.com")

        assertTrue(invalidEmail is Either.Error)
        assertEquals(EmailError.MALFORMED, invalidEmail.asError().error)
    }

    @Test
    fun `be the same even if it have upper case or extra spaces after or before email`() {
        val email = Email.valueOf("some_email@bankapp.com").asSuccess()
        val emailUpperCase = Email.valueOf("  Some_Email@bankapp.COM \n  ").asSuccess()

        assertEquals(email.value, emailUpperCase.value)
    }
}
