package com.kai.capybara.domain.model

sealed class Response<out F, out S> {

    data class Fail<out F> internal constructor(val value: F) : Response<F, Nothing>() {
        companion object {
            operator fun <F> invoke(f: F): Response<F, Nothing> = Fail(f)
        }
    }

    data class Success<out S> internal constructor(val value: S) : Response<Nothing, S>() {
        companion object {
            operator fun <S> invoke(s: S): Response<Nothing, S> = Success(s)
        }
    }

    companion object {
        fun <S> success(value: S): Response<Nothing, S> = Success(value)
        fun <F> fail(value: F): Response<F, Nothing> = Fail(value)
    }
}