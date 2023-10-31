package com.bankapp.infrastructure.database.firebase.home.dto

import com.bankapp.core.domain.Either
import com.bankapp.home.model.domain.Amount
import com.bankapp.home.model.domain.ExtraInformation
import com.bankapp.home.model.domain.Movement
import com.bankapp.home.model.domain.MovementId
import com.bankapp.home.model.domain.MovementName
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class MovementDTO(
    val movementId: String,
    val movementName: String,
    val amount: String,
    val extraInformation: String,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
) {
    /* no args constructor needed to deserialize */
    constructor() : this(
        movementId = "",
        movementName = "",
        amount = "",
        extraInformation = "",
    )

    fun toModel() = Movement(
        movementId = MovementId(movementId),
        movementName = MovementName(movementName),
        amount = amount.toAmount(),
        extraInformation = ExtraInformation(extraInformation),
        date = createdAt?.toDate() ?: Date()
    )

    private fun String.toAmount() = when(val amount = Amount.valueOf(this)) {
        is Either.Error -> Amount.Zero
        is Either.Success -> amount.value
    }
}
