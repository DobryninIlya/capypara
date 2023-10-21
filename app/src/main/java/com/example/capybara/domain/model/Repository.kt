package com.example.capybara.domain.model

import com.example.capybara.data.remote.CapyparaApi
import com.example.capybara.domain.model.schedule.Schedule
import com.example.capybara.domain.model.schedule.Group
import com.example.capybara.domain.model.schedule.Week
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface Repository {
    class UnavailableRepositoryException : Exception()

    fun isValidgroupName(groupName: Int): Boolean

    fun registerUser(user: User): String

    fun getSchedule(group: Group): Schedule

    fun getUser(uid: String): User?

    fun getWeek(): Week
    fun setUid(uid: String)
    fun isValidToken(uid: String): Boolean
    fun getGroup(groupName: Int): Group


    companion object {
        private val contentType = "application/json".toMediaType()
        private val json = Json

        private val api: CapyparaApi = Retrofit.Builder()
            .baseUrl("https://schedule-bot.kai.ru")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(CapyparaApi::class.java);

    }
}