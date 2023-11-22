package com.kai.capybara.domain.use_case

import com.kai.capybara.data.local.LocalStorages
import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.schedule.Schedule

class GetScheduleFromLocalUseCase(
    private val localStorage: LocalStorages
) {
    class ScheduleUnavailableException : Exception()

    fun invoke(): Schedule {
        return localStorage.schedule.get() ?: throw ScheduleUnavailableException()
    }
}