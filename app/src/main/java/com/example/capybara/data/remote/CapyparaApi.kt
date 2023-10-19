package com.example.capybara.data.remote

import com.example.capybara.domain.model.api.ResultWrapper
import com.example.capybara.domain.model.schedule.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CapyparaApi {

    @GET("api/schedule/{group_id}")
    fun getSchedule(
        @Path("group_id") groupId: Int,
        @Query("token") uid: String
    ): Call<List<DaySchedule>>

    @GET("api/groups/{group_number}")
    fun getGroupId(@Path("group_number") groupNumber: Int, @Query("token") uid: String): Call<ResultWrapper<Group>>

    @GET("/api/week")
    fun getWeek(): Call<ResultWrapper<Week>>


}