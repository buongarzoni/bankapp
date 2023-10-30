package com.bankapp.home.model.domain

import java.util.Date

data class Movement(
    val movementId: MovementId,
    val movementName: MovementName,
    val amount: Amount,
    val date: Date,
    val extraInformation: ExtraInformation,
)
