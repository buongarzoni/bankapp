package com.bankapp.onboarding.domain

import com.bankapp.core.domain.asSuccess

fun passwordDummy() = Password.valueOf("123456789").asSuccess()
