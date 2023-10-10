package com.example.capybara.domain.use_case

import com.example.capybara.data.local.SharedPreferenceManager
import com.example.capybara.domain.model.schedule.DaySchedule

class GetScheduleFromLocalUseCase(
    private val localStorage: SharedPreferenceManager
) {
    class ScheduleUnavailableException : Exception()

    fun invoke(): List<DaySchedule> {
        val schedule = localStorage.getSchedule()

        if (schedule.isEmpty()) throw ScheduleUnavailableException()

        return schedule
    }
}