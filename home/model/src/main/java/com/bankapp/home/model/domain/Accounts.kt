package com.bankapp.home.model.domain

import com.bankapp.core.domain.Either
import com.bankapp.core.user.UserId

interface Accounts {
    suspend fun getBankDetails(userId: UserId): Either<Int, BankDetails>
}
