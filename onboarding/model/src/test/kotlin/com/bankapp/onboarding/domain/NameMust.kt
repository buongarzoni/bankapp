package com.bankapp.onboarding.domain

import com.bankapp.core.domain.asError
import com.bankapp.core.domain.asSuccess
import org.junit.Assert.assertEquals
import org.junit.Test

class NameMust {

    @Test
    fun `return Long error when name contains more than 20 characters`() {
        val longNameError = Name.valueOf("012345678901234567891").asError()

        assertEquals(NameError.LONG, longNameError)
    }

    @Test
    fun `return Short error when name contains 1 or no character`() {
        val shortNameError = Name.valueOf("x").asError()

        assertEquals(NameError.SHORT, shortNameError)
    }

    @Test
    fun `return Invalid Character error when name contains an unexpected character`() {
        val invalidCharactersError = Name.valueOf("abcdefg!hijklmn").asError()

        assertEquals(NameError.INVALID_CHARACTER, invalidCharactersError)
    }

    @Test
    fun `trim extra spaces`() {
        val name = Name.valueOf("christian").asSuccess()
        val nameWithExtraSpacesRemoved = Name.valueOf(" christian \n ").asSuccess()

        assertEquals(name, nameWithExtraSpacesRemoved)
    }
}
