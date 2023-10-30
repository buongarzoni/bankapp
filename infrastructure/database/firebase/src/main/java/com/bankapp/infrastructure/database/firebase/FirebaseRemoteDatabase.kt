package com.bankapp.infrastructure.database.firebase

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.core.user.UserId
import com.bankapp.infrastructure.database.firebase.onboarding.register.NewUserDTO
import com.bankapp.onboarding.domain.RegistrationData
import com.bankapp.onboarding.domain.Users
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirebaseRemoteDatabase(
    private val database: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val storage: FirebaseStorage = FirebaseStorage.getInstance(),
) : Users {

    override suspend fun register(
        userId: UserId,
        registrationData: RegistrationData
    ): Either<Int, Unit> {
        return when (val saveIdPhotoResult = saveIdPhoto(userId, registrationData)) {
            is Either.Error -> return saveIdPhotoResult.error.error()
            is Either.Success -> {
                val newUserDTO = NewUserDTO.from(userId, registrationData, saveIdPhotoResult.value)
                saveUserData(newUserDTO)
            }
        }
    }

    private suspend fun saveIdPhoto(
        userId: UserId,
        registrationData: RegistrationData,
    ): Either<Int, String> {
        var error: Int? = null
        var path: String? = null

        val task = saveIdPhotoTask(userId, registrationData)
            .addOnSuccessListener { path = it.storage.path }
            .addOnFailureListener { error = uploadPhotoError }
        Tasks.whenAllComplete(task).await()

        return if (error != null) error!!.error() else path!!.success()
    }

    private suspend fun saveUserData(newUserDTO: NewUserDTO): Either<Int, Unit> {
        var error: Int? = null

        val task = saveUserTask(newUserDTO).addOnFailureListener { error = savingUserError }
        Tasks.whenAllComplete(task).await()

        return if (error != null) error!!.error() else Unit.success()
    }

    private fun saveIdPhotoTask(userId: UserId, registrationData: RegistrationData) = storage
        .getReference(USER_STORAGE_FOLDER)
        .child(userId.id)
        .child(ID_PHOTO_STORAGE_FOLDER)
        .putBytes(registrationData.byteArray)

    private fun saveUserTask(newUserDTO: NewUserDTO) = database
        .collection(USERS_DATABASE_PATH)
        .document(newUserDTO.userId)
        .set(newUserDTO)

    private val uploadPhotoError =
        R.string.infrastructure_database_firebase_error_can_not_upload_photo
    private val savingUserError = R.string.infrastructure_database_firebase_error_can_not_save_user

    companion object {
        private const val USERS_DATABASE_PATH = "users"
        private const val USER_STORAGE_FOLDER = "user"
        private const val ID_PHOTO_STORAGE_FOLDER = "id"
    }
}
