package com.bankapp.infrastructure.database.firebase.home.dto

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
    val amountDTO: AmountDTO,
    val extraInformation: String,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
) {
    /* no args constructor needed to deserialize */
    constructor() : this(
        movementId = "",
        movementName = "",
        amountDTO = AmountDTO(),
        extraInformation = "",
    )

    fun toModel() = Movement(
        movementId = MovementId(movementId),
        movementName = MovementName(movementName),
        amount = amountDTO.toModel(),
        extraInformation = ExtraInformation(extraInformation),
        date = createdAt?.toDate() ?: Date()
    )
}
