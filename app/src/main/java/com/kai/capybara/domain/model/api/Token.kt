package com.kai.capybara.domain.model.api

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    var token: String
)