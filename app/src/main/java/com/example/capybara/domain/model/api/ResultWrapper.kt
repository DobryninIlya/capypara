package com.example.capybara.domain.model.api

import kotlinx.serialization.Serializable

@Serializable
data class ResultWrapper<T>(val result: T)




