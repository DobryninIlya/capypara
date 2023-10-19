package com.example.capybara.domain.use_case

import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.util.rebuildSchedule


class GetScheduleUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {
    class UnRegisteredUserException : Exception()

    fun invoke(): List<DaySchedule> {

        val uid: String? = localStorage.getUid();

        if (uid.isNullOrEmpty()) throw UnRegisteredUserException()

        try {

            if (repository.isValidToken(uid)) {
                throw UnRegisteredUserException()
            }

            val schedule = repository.getSchedule(localStorage.getGroupNumber())

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