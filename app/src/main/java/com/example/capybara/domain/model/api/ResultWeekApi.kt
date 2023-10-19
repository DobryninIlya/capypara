package com.example.capybara.domain.model.api

import kotlinx.serialization.Serializable


@Serializable
data class ResultWeekApi(
    val week_parity: Int = 1
)
