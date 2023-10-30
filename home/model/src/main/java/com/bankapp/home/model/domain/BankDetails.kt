package com.bankapp.home.model.domain

data class BankDetails(
    val accountBalance: AccountBalance,
    val movements: List<Movement>,
)
