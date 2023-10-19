package com.example.capybara.domain.model

import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.model.schedule.WeekType

interface Repository {
    class UnavailableRepositoryException : Exception()

    fun isValidGroupNumber(groupNumber: Int): Boolean

    fun registerUser(): String

    fun getSchedule(groupId: Int): List<DaySchedule>

    fun getUser(uid: String): User?

    fun getWeekType(): WeekType
    fun setUid(uid: String)
    abstract fun isValidToken(uid: String): Boolean
}