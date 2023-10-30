package com.bankapp.core.auth

import com.bankapp.core.domain.Either
import com.bankapp.core.user.UserId

interface Auth {
    fun getLoggedUserId(): Either<Int, UserId>
}
