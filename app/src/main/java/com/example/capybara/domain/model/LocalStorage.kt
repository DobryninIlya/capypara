package com.example.capybara.domain.model

import com.example.capybara.domain.model.schedule.Schedule
import com.example.capybara.domain.model.schedule.Group

interface LocalStorage {
    fun saveUid(uid: String)
    fun getUid(): String?

    fun saveSchedule(schedule: Schedule)

    fun getSchedule(): List<Schedule>
    fun saveGroupNumber(uid: String)
    fun getGroup(): Group


}