package com.bankapp.core.domain

sealed interface Either<L, R> {
    class Error<L, R>(val error: L) : Either<L, R>
    class Success<L, R>(val value: R) : Either<L, R>

    suspend fun <T> fold(fnL: (L) -> T, fnR: suspend (R) -> T): T {
        return when (this) {
            is Error -> fnL(error)
            is Success -> fnR(value)
        }
    }

    suspend fun <R2> map(fn: suspend (R) -> Either<L, R2>): Either<L, R2> {
        return when (this) {
            is Error -> Error(this.error)
            is Success -> fn(value)
        }
    }
}

fun <L, R> R.success() = Either.Success<L, R>(this)
fun <L, R> L.error() = Either.Error<L, R>(this)

fun <L, R> Either<L, R>.asSuccess() = (this as Either.Success).value
fun <L, R> Either<L, R>.asError() = (this as Either.Error).error
