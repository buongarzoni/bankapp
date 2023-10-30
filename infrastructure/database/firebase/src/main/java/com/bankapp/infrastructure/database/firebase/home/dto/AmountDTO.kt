package com.bankapp.infrastructure.database.firebase.home.dto

import androidx.annotation.Keep
import com.bankapp.core.domain.Either
import com.bankapp.home.model.domain.Amount

@Keep
data class AmountDTO(
    val value: String,
) {
    /* no args constructor needed to deserialize */
    constructor() : this(
        value = "$ 0"
    )

    fun toModel() = when(val result = Amount.valueOf(value)) {
            is Either.Error -> Amount.Zero
            is Either.Success -> result.value
        }
}
