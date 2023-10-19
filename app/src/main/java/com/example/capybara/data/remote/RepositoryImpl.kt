package com.example.capybara.data.remote

import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.Repository.UnavailableRepositoryException
import com.example.capybara.domain.model.User
import com.example.capybara.domain.model.api.Result
import com.example.capybara.domain.model.api.ResultWrapper
import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.model.schedule.WeekType
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import java.net.SocketTimeoutException


class RepositoryImpl : Repository {

    val contentType = "application/json".toMediaType()
    private val json = Json

    private val api: CapyparaApi = Retrofit.Builder()
        .baseUrl("https://schedule-bot.kai.ru")
        .addConverterFactory(json.asConverterFactory(contentType))
        .build().create(CapyparaApi::class.java);

    class UidNotSetException : Exception()
    class NotInfoException : Exception()

    private var uid: String = ""

    override fun isValidGroupNumber(groupNumber: Int): Boolean {
        return false
    }


    fun getGroupId(groupNumber: Int): ResultWrapper<Int>? {
        try {
            return api.getGroupId(groupNumber, uid).execute().body()
        }
        catch (e: SocketTimeoutException) {
            throw UnavailableRepositoryException()
        }
    }

    override fun registerUser(): String {
        return ""
    }

    override fun getSchedule(groupId: Int): List<DaySchedule> =
        api.getSchedule(
            uid = uid, groupId = groupId
        ).execute().body()!!

    private fun isUidSet(): Boolean {
        return uid != ""
    }

    override fun getUser(uid: String): User? {
        return TODO("Provide the return value")
    }

    override fun getWeekType(): WeekType {
        val result = api.getWeek().execute().body()
        if (result?.week_parity == 0) return WeekType.Even
        return WeekType.Uneven
    }

    override fun setUid(uid: String) {
        this.uid = uid
    }

    override fun isValidToken(uid: String): Boolean {
        TODO("Not yet implemented")
    }
}