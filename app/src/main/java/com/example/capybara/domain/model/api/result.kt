package com.example.capybara.domain.model.api
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class ResultWrapper<T>(val result: Result<T>)
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Result<T>(@JsonNames("group_id") val filed: T)




