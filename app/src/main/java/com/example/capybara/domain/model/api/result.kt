package com.example.capybara.domain.model.api
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class ResultWrapper<T>(val result: T)




