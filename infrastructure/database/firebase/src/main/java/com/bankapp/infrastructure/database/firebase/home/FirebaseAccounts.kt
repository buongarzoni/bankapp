package com.bankapp.infrastructure.database.firebase.home

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.core.user.UserId
import com.bankapp.home.model.domain.AccountBalance
import com.bankapp.home.model.domain.Accounts
import com.bankapp.home.model.domain.Amount
import com.bankapp.home.model.domain.BankDetails
import com.bankapp.infrastructure.database.firebase.R
import com.bankapp.infrastructure.database.firebase.home.dto.BankDetailsDTO
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAccounts(
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance(),
): Accounts {
    override suspend fun getBankDetails(userId: UserId): Either<Int, BankDetails> {
        var result: Either<Int, BankDetails> = unknownError.error()

        val task = database.collection("accounts").document(userId.id)
            .get()
            .addOnSuccessListener {
                val bankDetails = it.toObject(BankDetailsDTO::class.java)?.toModel() ?: emptyBankDetails
                result = bankDetails.success()
            }
            .addOnFailureListener {
                result = fetchError.error()
            }
        Tasks.whenAllComplete(task).await()

        return result
    }

    private val emptyBankDetails = BankDetails(
        accountBalance = AccountBalance(Amount.Zero, Amount.Zero),
        movements = emptyList(),
    )
    private val unknownError = R.string.infrastructure_database_firebase_error_unknown
    private val fetchError = R.string.infrastructure_database_firebase_error_fetching_bank_details
}
