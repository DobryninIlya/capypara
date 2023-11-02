package com.kai.capybara.data.remote

import com.kai.capybara.domain.model.api.RegisterUserRequest
import com.kai.capybara.domain.model.api.ResultWrapper
import com.kai.capybara.domain.model.api.Token
import com.kai.capybara.domain.model.schedule.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CapyparaApi {

    @GET("api/schedule/{group_id}")
    fun getSchedule(
        @Path("group_id") groupId: Int, @Query("token") uid: String
    ): Call<ResultWrapper<ScheduleResult>>

    @GET("api/groups/{group_number}")
    fun getGroupId(
        @Path("group_number") groupName: Int, @Query("token") uid: String
    ): Call<ResultWrapper<Group>>

    @GET("/api/week")
    fun getWeek(): Call<ResultWrapper<Week>>

    @POST("/api/token")
    fun registerUser(@Body request: RegisterUserRequest): Call<ResultWrapper<Token>>

    companion object {
        fun get(): CapyparaApi =
            Retrofit.Builder()
                .baseUrl("https://schedule-bot.kai.ru")
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CapyparaApi::class.java);

    }

}