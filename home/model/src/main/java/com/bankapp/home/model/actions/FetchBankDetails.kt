package com.bankapp.home.model.actions

import com.bankapp.core.auth.Auth
import com.bankapp.core.domain.Either
import com.bankapp.home.model.domain.Accounts
import com.bankapp.home.model.domain.BankDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchBankDetails(
    private val auth: Auth,
    private val accounts: Accounts,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
){

    suspend fun execute(): Either<Int, BankDetails> = withContext(dispatcher) {
        when(val userId = auth.getLoggedUserId()) {
            is Either.Error -> error("Shouldn't happen") //todo improve
            is Either.Success -> {
                accounts.getBankDetails(userId.value)
            }
        }
    }

}
