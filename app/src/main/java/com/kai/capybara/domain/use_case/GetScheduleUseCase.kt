package com.kai.capybara.domain.use_case

import com.kai.capybara.data.local.LocalStorages
import com.kai.capybara.domain.model.DateTime
import com.kai.capybara.data.remote.Remotes
import com.kai.capybara.domain.model.Repositories1
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.util.rebuildSchedule
import java.util.Calendar


class GetScheduleUseCase(
    private val repositories: Remotes,
    private val localStorage: LocalStorages,
) {
    class UnRegisteredUserException : Exception()

    fun invoke(): Schedule {

        val uid: String? = localStorage.user.getUid();

        if (uid.isNullOrEmpty()) throw UnRegisteredUserException()

        try {

            if (repositories.user.isValidToken(uid)) {
               throw UnRegisteredUserException()
            }

            val schedule = repositories.schedule.get(localStorage.user.getGroup())

            val week = repositories.schedule.getWeek()

            localStorage.schedule.save(schedule)

            return rebuildSchedule(
                schedule,
                week,
                DateTime(Calendar.getInstance())
            )

        } catch (e: Repositories1.UnavailableRepositoryException) {
            return GetScheduleFromLocalUseCase(localStorage).invoke()
        }

    }
}