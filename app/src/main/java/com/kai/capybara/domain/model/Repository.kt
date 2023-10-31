package com.kai.capybara.domain.model

import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group
import com.kai.capybara.domain.model.schedule.Week
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface Repository {
    class UnavailableRepositoryException(val string: String = "") : Exception() {
        override val message: String
            get() = string
    }

    fun isValidGroupName(groupName: Int): Boolean

    fun registerUser(user: User): User

    fun getSchedule(group: Group): Schedule

    fun getUser(uid: String): User?

    fun getWeek(): Week
    fun setToken(uid: String)
    fun isValidToken(uid: String): Boolean
    fun getGroup(groupName: Int): Group
}