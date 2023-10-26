package com.bankapp.core.domain

sealed interface Either<L, R> {
    class Error<L, R>(val error: R) : Either<L, R>
    class Success<L, R>(val value: L) : Either<L, R>
}

fun <L, R> L.success() = Either.Success<L, R>(this)
fun <L, R> R.error() = Either.Error<L, R>(this)

fun <L, R> Either<L, R>.asSuccess() = this as Either.Success
fun <L, R> Either<L, R>.asError() = this as Either.Error
