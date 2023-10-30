package com.bankapp.infrastructure.database.firebase.onboarding.register

import androidx.annotation.Keep
import com.bankapp.core.user.UserId
import com.bankapp.onboarding.domain.RegistrationData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

@Keep
data class NewUserDTO(
    val userId: String,
    val email: String,
    val name: String,
    val lastName: String,
    val idPicturePath: String,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
) {

    companion object {
        fun from(
            userId: UserId,
            registrationData: RegistrationData,
            idPicturePath: String,
        ) = NewUserDTO(
            userId = userId.id,
            email = registrationData.email.value,
            name = registrationData.name.value,
            lastName = registrationData.lastName.value,
            idPicturePath = idPicturePath,
        )
    }

}
