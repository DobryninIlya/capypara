package com.kai.capybara.data.remote

import android.util.Log
import com.kai.capybara.domain.model.RegisterInterface
import com.kai.capybara.domain.model.Repository
import com.kai.capybara.domain.model.Repository.UnavailableRepositoryException
import com.kai.capybara.domain.model.User
import com.kai.capybara.domain.model.api.Error
import com.kai.capybara.domain.model.api.RegisterUserRequest
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group
import com.kai.capybara.domain.model.schedule.Week
import com.kai.capybara.domain.util.isConnectingException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.readJson
import okio.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class RepositoryImpl(val api: CapyparaApi, val firebase: RegisterInterface) : Repository {


    class TokenNotSetException : Exception()
    class NoInfoException : Exception()

    private var token: String = ""


    private fun <T> handleConnectingException(block: () -> T): T {
        try {

            return block()

        } catch (e: Exception) {

            throw if (isConnectingException(e))
                UnavailableRepositoryException()
            else e

        }
    }

    override fun isValidGroupName(groupName: Int): Boolean {
        return try {

            getGroup(groupName)
            true

        } catch (e: NoInfoException) {

            false

        }
    }


    override fun getGroup(groupName: Int): Group {

        isTokenSet()

        return handleConnectingException {

            val group = api.getGroupId(groupName, token).execute().body()?.result
                ?: throw NoInfoException()

            return@handleConnectingException Group(group.group_id, groupName)
        }


    }

    override fun registerUser(user: User): User = handleConnectingException {

        user.uid = firebase.getNewUid()

        val res = api.registerUser(RegisterUserRequest(user)).execute()

        if (res.isSuccessful) {

            user.token = res.body()!!.result.token

        } else {

            throw UnavailableRepositoryException(
                Json.decodeFromString<Error>(
                    res.errorBody()!!.string()
                ).error
            )

        }

        return@handleConnectingException user

    }

    override fun getSchedule(group: Group): Schedule = handleConnectingException {

        isTokenSet()

        return@handleConnectingException api.getSchedule(uid = token, groupId = group.group_id)
            .execute()
            .body()?.result?.schedule
            ?: throw NoInfoException()
    }

    private fun isTokenSet() {
        if (token == "") throw TokenNotSetException()
    }

    override fun getUser(uid: String): User? {
        return TODO("Provide the return value")
    }

    override fun getWeek(): Week = handleConnectingException {

        return@handleConnectingException api.getWeek().execute().body()?.result
            ?: throw NoInfoException()

    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun isValidToken(uid: String): Boolean {
        TODO("Not yet implemented")
    }
}