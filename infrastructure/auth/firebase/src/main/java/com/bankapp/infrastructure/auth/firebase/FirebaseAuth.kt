package com.bankapp.infrastructure.auth.firebase

import com.bankapp.core.domain.Either
import com.bankapp.core.domain.error
import com.bankapp.core.domain.success
import com.bankapp.core.user.UserId
import com.bankapp.onboarding.domain.Email
import com.bankapp.onboarding.domain.Password
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await
import com.bankapp.core.auth.Auth as AuthCore
import com.bankapp.onboarding.domain.Auth as AuthOnboarding

class FirebaseAuth(
    private val firebaseAuth: FirebaseAuth,
) : AuthOnboarding, AuthCore {
    private val noLoggedUserError = R.string.infrastructure_auth_firebase_error_no_logged_user
    private val invalidIdError = R.string.infrastructure_auth_firebase_error_invalid_id
    private val emailCollisionError = R.string.infrastructure_auth_firebase_error_email_collision
    private val weakPasswordError = R.string.infrastructure_auth_firebase_error_weak_password
    private val invalidCredentialsError = R.string.infrastructure_auth_firebase_error_invalid_credentials
    private val unknownError = R.string.infrastructure_auth_firebase_error_unknown_error

    override fun getLoggedUserId(): Either<Int, UserId> {
        val id = firebaseAuth.currentUser?.uid ?: return noLoggedUserError.error()

        return when (val userId = UserId.valueOf(id)) {
            is Either.Error -> invalidIdError.error() // shouldn't happen
            is Either.Success -> userId.value.success()
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: Email,
        password: Password
    ): Either<Int, Unit> {
        var error: Int? = null

        firebaseAuth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnFailureListener {
                error = when (it) {
                    is FirebaseAuthUserCollisionException -> emailCollisionError

                    is FirebaseAuthWeakPasswordException -> weakPasswordError

                    is FirebaseAuthInvalidCredentialsException -> invalidCredentialsError

                    else -> {
                        //Todo add log
                        unknownError
                    }
                }
            }
            .await()

        return if (error == null) Unit.success() else error!!.error()
    }
}
