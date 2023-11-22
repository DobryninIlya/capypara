package com.kai.capybara.domain.model

enum class ResponseErrors {
    EMPTY,
    UNREGISTERED_USER,
    UNKNOWN,
}


class NoInfoException: Exception()


fun parseResponseError(value: String): ResponseErrors =
    when(value) {
        "bad token" -> ResponseErrors.UNREGISTERED_USER
        else -> ResponseErrors.EMPTY
    }


