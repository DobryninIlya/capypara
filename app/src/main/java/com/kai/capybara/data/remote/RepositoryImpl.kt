package com.kai.capybara.data.remote

import com.kai.capybara.domain.model.RegisterInterface
import com.kai.capybara.domain.model.Repository
import com.kai.capybara.domain.model.Repository.UnavailableRepositoryException
import com.kai.capybara.domain.model.User
import com.kai.capybara.domain.model.api.RegisterUserRequest
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group
import com.kai.capybara.domain.model.schedule.Week
import okio.IOException
import java.net.SocketTimeoutException


class RepositoryImpl(val api: CapyparaApi, val firebase: FirebaseManager) : Repository {


    class TokenNotSetException : Exception()
    class NoInfoException : Exception()

    private var token: String = ""

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

        try {

            val group = api.getGroupId(groupName, token).execute().body()?.result
                ?: throw NoInfoException()

            return Group(group.group_id, groupName)

        } catch (e: SocketTimeoutException) {

            throw UnavailableRepositoryException()

        }
    }

    override fun registerUser(user: User): User {

        user.uid = firebase.getNewUid()

        val res = api.registerUser(RegisterUserRequest(user)).execute()

        if (res.isSuccessful) {
            //TODO(return error)
            res.errorBody().toString()
        } else {
            user.token = res.body()!!.result.token
        }
        return user
    }

    override fun getSchedule(group: Group): Schedule {

        isTokenSet()

        return api.getSchedule(uid = token, groupId = group.group_id).execute()
            .body()?.result?.schedule
            ?: throw NoInfoException()
    }

    private fun isTokenSet() {
        if (token == "") throw TokenNotSetException()
    }

    override fun getUser(uid: String): User? {
        return TODO("Provide the return value")
    }

    override fun getWeek(): Week {
        try {

            return api.getWeek().execute().body()?.result
                ?: throw NoInfoException()

        } catch (e: IOException) {

            throw UnavailableRepositoryException()

        }
    }

    override fun setUid(uid: String) {
        this.token = uid
    }

    override fun isValidToken(uid: String): Boolean {
        TODO("Not yet implemented")
    }
}