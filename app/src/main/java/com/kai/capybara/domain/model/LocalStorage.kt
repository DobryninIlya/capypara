package com.kai.capybara.domain.model

import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group

interface LocalStorage {
    fun getString(key: String): String?
    fun putString(key: String, value: String)

    fun getInt(key: String): Int
    fun putInt(key: String, value: Int)
}