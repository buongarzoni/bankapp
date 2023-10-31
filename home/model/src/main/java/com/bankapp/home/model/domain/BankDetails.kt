package com.bankapp.home.model.domain

data class BankDetails(
    val accountBalance: AccountBalance,
    val movements: List<Movement>,
) {
    companion object {
        val Empty = BankDetails(
            accountBalance = AccountBalance(Amount.Zero, Amount.Zero),
            movements = emptyList(),
        )
    }
}
