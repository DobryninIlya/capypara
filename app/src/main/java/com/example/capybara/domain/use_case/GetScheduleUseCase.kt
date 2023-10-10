package com.example.capybara.domain.use_case

import com.example.capybara.data.local.SharedPreferenceManager
import com.example.capybara.data.remote.Repository
import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.util.rebuildSchedule


class GetScheduleUseCase(
    private val repository: Repository,
    private val localStorage: SharedPreferenceManager,
) {
    class UnRegisteredUserException : Exception()
    class ScheduleUnavailableException : Exception()

    fun invoke(): List<DaySchedule> {

        val uid: String? = localStorage.getUid();

        if (uid.isNullOrEmpty()) throw UnRegisteredUserException()

        try {

            val user = repository.getUser(uid) ?: throw UnRegisteredUserException()

            val schedule = repository.getSchedule(user.groupNumber)

            val weekType = repository.getWeekType()

            localStorage.saveSchedule(schedule)

            return rebuildSchedule(
                schedule,
                weekType,
            )

        } catch (e: Repository.UnavailableRepositoryException) {
            return GetScheduleFromLocalUseCase(localStorage).invoke()
        }

    }
}