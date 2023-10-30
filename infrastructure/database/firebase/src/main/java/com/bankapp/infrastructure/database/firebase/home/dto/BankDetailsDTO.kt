package com.bankapp.infrastructure.database.firebase.home.dto

import androidx.annotation.Keep
import com.bankapp.core.domain.Either
import com.bankapp.home.model.domain.AccountBalance
import com.bankapp.home.model.domain.Amount
import com.bankapp.home.model.domain.BankDetails

@Keep
data class BankDetailsDTO(
    val accountBalance: Map<String, String>,
    val movements: List<MovementDTO>,
) {
    /* no args constructor needed to deserialize */
    constructor() : this(
        accountBalance = emptyMap(),
        movements = emptyList(),
    )

    fun toModel() = BankDetails(
        accountBalance = AccountBalance(
            income = getAmount("income"),
            expense = getAmount("expense"),
        ),
        movements = movements.map { it.toModel() },
    )

    private fun getAmount(field: String) = when(val amount = Amount.valueOf(accountBalance[field] ?: "")) {
        is Either.Error -> Amount.Zero
        is Either.Success -> amount.value
    }
}
