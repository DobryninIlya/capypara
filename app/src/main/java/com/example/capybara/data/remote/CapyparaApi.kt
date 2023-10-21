package com.example.capybara.data.remote

import com.example.capybara.domain.model.User
import com.example.capybara.domain.model.api.RegisterUser
import com.example.capybara.domain.model.api.ResultWrapper
import com.example.capybara.domain.model.api.Token
import com.example.capybara.domain.model.schedule.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CapyparaApi {

    @GET("api/schedule/{group_id}")
    fun getSchedule(
        @Path("group_id") groupId: Int,
        @Query("token") uid: String
    ): Call<ResultWrapper<ScheduleResult>>

    @GET("api/groups/{group_number}")
    fun getGroupId(
        @Path("group_number") groupName: Int,
        @Query("token") uid: String
    ): Call<ResultWrapper<Group>>

    @GET("/api/week")
    fun getWeek(): Call<ResultWrapper<Week>>

    @GET("/api/token")
    fun registerUser(@Body request: RegisterUser): Call<ResultWrapper<Token>>

    companion object {
        fun get(): CapyparaApi {
            val contentType = "application/json".toMediaType()
            val json = Json

            val api: CapyparaApi = Retrofit.Builder()
                .baseUrl("https://schedule-bot.kai.ru")
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
                .create(CapyparaApi::class.java);

            return api
        }
    }

}