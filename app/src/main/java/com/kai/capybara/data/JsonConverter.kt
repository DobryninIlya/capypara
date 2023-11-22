package com.kai.capybara.data

import com.kai.capybara.domain.model.schedule.Schedule
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

class JsonConverter {
    companion object {
        inline fun <reified T> convertFromString(value: String): T = Json.decodeFromString<T>(value)
        inline fun <reified T> fromClassToString(jsonClass: T): String =
            Json.encodeToJsonElement<T>(jsonClass).toString()

    }
}