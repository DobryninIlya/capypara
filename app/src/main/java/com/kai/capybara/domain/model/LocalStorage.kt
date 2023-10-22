package com.kai.capybara.domain.model

import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group

interface LocalStorage {
    fun saveToken(uid: String)
    fun getUid(): String?

    fun saveSchedule(schedule: Schedule)

    fun getSchedule(): List<Schedule>
    fun saveGroupName(groupName: Int)
    fun getGroup(): Group


}