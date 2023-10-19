package com.example.capybara.domain.use_case

import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.schedule.DaySchedule

class GetScheduleFromLocalUseCase(
    private val localStorage: LocalStorage
) {
    class ScheduleUnavailableException : Exception()

    fun invoke(): List<DaySchedule> {
        val schedule = localStorage.getSchedule()

        if (schedule.isEmpty()) throw ScheduleUnavailableException()

        return schedule
    }
}