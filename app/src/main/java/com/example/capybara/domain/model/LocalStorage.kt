package com.example.capybara.domain.model

import android.content.Context
import android.content.SharedPreferences
import com.example.capybara.R
import com.example.capybara.domain.model.schedule.DaySchedule

interface LocalStorage {
    fun saveUid(uid: String)
    fun getUid(): String?

    fun saveSchedule(schedule: List<DaySchedule>)

    fun getSchedule(): List<DaySchedule>
    fun saveGroupNumber(uid: String)
    fun getGroupNumber(): Int


}