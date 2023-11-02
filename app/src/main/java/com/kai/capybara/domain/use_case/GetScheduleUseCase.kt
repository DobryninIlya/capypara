package com.kai.capybara.domain.use_case

import com.kai.capybara.domain.model.DateTime
import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.Repository
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.util.rebuildSchedule
import java.util.Calendar


class GetScheduleUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {
    class UnRegisteredUserException : Exception()

    fun invoke(): Schedule {

        val uid: String? = localStorage.getUid();

        if (uid.isNullOrEmpty()) throw UnRegisteredUserException()

        try {

            if (repository.isValidToken(uid)) {
                throw UnRegisteredUserException()
            }

            val schedule = repository.getSchedule(localStorage.getGroup())

            val week = repository.getWeek()

            localStorage.saveSchedule(schedule)

            return rebuildSchedule(
                schedule,
                week,
                DateTime(Calendar.getInstance())
            )

        } catch (e: Repository.UnavailableRepositoryException) {
            return GetScheduleFromLocalUseCase(localStorage).invoke()
        }

    }
}