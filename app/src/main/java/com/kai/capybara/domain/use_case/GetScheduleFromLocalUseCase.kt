package com.kai.capybara.domain.use_case

import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.schedule.Schedule

class GetScheduleFromLocalUseCase(
    private val localStorage: LocalStorage
) {
    class ScheduleUnavailableException : Exception()

    fun invoke(): Schedule {
        return localStorage.getSchedule() ?: throw ScheduleUnavailableException()
    }
}