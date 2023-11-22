package com.kai.capybara.data.remote

import com.kai.capybara.domain.model.NoInfoException
import com.kai.capybara.domain.model.Response
import com.kai.capybara.domain.model.ResponseErrors
import com.kai.capybara.domain.use_case.GetScheduleUseCase
import com.kai.capybara.domain.util.isConnectingException


class Remotes(
    val user: UserRemote,
    val schedule: ScheduleRemote,
) {


    fun isRepoAvailable(): Boolean {
        try {
            schedule.getWeek()
        } catch (e: Exception) {
            if (isConnectingException(e)) return false
        }
        return true
    }


}

open class Remote(protected val request: Request) {
    protected fun processResponseError(error: ResponseErrors) {
        when (error) {
            ResponseErrors.UNREGISTERED_USER -> throw GetScheduleUseCase.UnRegisteredUserException()
            ResponseErrors.EMPTY -> throw NoInfoException()
            else -> throw UnknownError()
        }
    }

    protected fun <T> processResponse(response: Response<ResponseErrors, T>): T {
        if (response is Response.Fail) processResponseError(response.value)
        return response.value
    }
}