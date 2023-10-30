package com.bankapp.onboarding.domain

import com.bankapp.core.domain.asSuccess

fun emailDummy() = Email.valueOf("some_email@bankapp.com").asSuccess()
