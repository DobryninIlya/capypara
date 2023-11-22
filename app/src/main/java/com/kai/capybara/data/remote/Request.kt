package com.kai.capybara.data.remote

import com.kai.capybara.data.JsonConverter
import com.kai.capybara.domain.model.Response
import com.kai.capybara.domain.model.ResponseErrors
import com.kai.capybara.domain.model.api.Error
import com.kai.capybara.domain.model.api.RegisterUserRequest
import com.kai.capybara.domain.model.api.ResultWrapper
import com.kai.capybara.domain.model.api.Token
import com.kai.capybara.domain.model.parseResponseError
import com.kai.capybara.domain.model.schedule.Group
import com.kai.capybara.domain.model.schedule.ScheduleResult
import com.kai.capybara.domain.model.schedule.Week
import com.kai.capybara.domain.use_case.GetScheduleUseCase
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

class Request {
    private val api = CapyparaApi.get()

    private fun <T> processResponse(response: retrofit2.Response<T>): Response<ResponseErrors, T> {
        if (response.isSuccessful) {
            return Response.Success(response.body()!!)
        }
        return Response.fail(
            parseResponseError(
                JsonConverter.convertFromString<Error>(
                    response.errorBody()!!.string()
                ).error
            )
        )
    }

    fun getSchedule(
        groupId: Int, token: String
    ): Response<ResponseErrors, ResultWrapper<ScheduleResult>?> =
        processResponse(api.getSchedule(groupId, token).execute())

    fun getGroupId(
        groupName: Int, uid: String
    ): Response<ResponseErrors, ResultWrapper<Group>> =
        processResponse(api.getGroupId(groupName, uid).execute())

    fun getWeek() = processResponse(api.getWeek().execute())

    fun registerUser(request: RegisterUserRequest) =
        processResponse(api.registerUser(request).execute())

    fun isValidToken(token: String): Boolean =
        processResponse(api.getWhoIAm(token).execute()) !is Response.Fail


}