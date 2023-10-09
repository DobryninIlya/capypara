package com.example.capybara.domain.use_case

import com.example.capybara.data.local.SharedPreferenceManager
import com.example.capybara.data.remote.Repository
import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.util.rebuildSchedule


class GetScheduleUseCase(
    private val repository: Repository,
    private val localStorage: SharedPreferenceManager,
) {
    fun invoke(): List<DaySchedule> {

        val uid: String = localStorage.getUid();
        val groupNumber = repository.getUser(uid).groupNumber
        val schedule = repository.getSchedule(groupNumber)
        val weekType = repository.getWeekType()

        return rebuildSchedule(
            schedule,
            weekType,
        )
    }

}
