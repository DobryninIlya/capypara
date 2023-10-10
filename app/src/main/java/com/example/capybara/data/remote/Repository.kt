package com.example.capybara.data.remote

import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.model.User
import com.example.capybara.domain.model.schedule.WeekType

class Repository {

    class EmptyAnswerException : Exception()
    class UnavailableRepositoryException : Exception()

    fun isValidGroupNumber(groupNumber: Int): Boolean {
        return TODO("Provide the return value")
    }

    fun getUid(): String {
        return TODO("Provide the return value")
    }

    fun getSchedule(groupNumber: Int): List<DaySchedule> {
        return TODO("Provide the return value")
    }

    fun getUser(uid: String): User? {
        return TODO("Provide the return value")
    }

    fun getWeekType(): WeekType {
        return TODO("Provide the return value")
    }
}