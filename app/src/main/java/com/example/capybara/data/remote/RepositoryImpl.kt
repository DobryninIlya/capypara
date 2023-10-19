package com.example.capybara.data.remote

import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.Repository.UnavailableRepositoryException
import com.example.capybara.domain.model.User
import com.example.capybara.domain.model.schedule.DaySchedule
import com.example.capybara.domain.model.schedule.Group
import com.example.capybara.domain.model.schedule.Week
import okio.IOException
import java.net.SocketTimeoutException


class RepositoryImpl : Repository {


    class UidNotSetException : Exception()
    class NoInfoException : Exception()

    private var uid: String = ""

    private val api = CapyparaApi.build()

    override fun isValidGroupNumber(groupNumber: Int): Boolean {
        return try {
            getGroup(groupNumber)
            true
        } catch (e: NoInfoException) {
            false
        }
    }


    override fun getGroup(groupNumber: Int): Group {
        isUidSet()

        try {

            val group = api.getGroupId(groupNumber, uid).execute().body()?.result
                ?: throw NoInfoException()

            return Group(group.groupId, groupNumber)

        } catch (e: SocketTimeoutException) {

            throw UnavailableRepositoryException()

        }
    }

    override fun registerUser(): String {
        return ""
    }

    override fun getSchedule(groupId: Int): List<DaySchedule> {

        isUidSet()

        return api.getSchedule(uid = uid, groupId = groupId).execute().body()
            ?: throw NoInfoException()
    }

    private fun isUidSet() {
        if (uid == "") throw UidNotSetException()
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
        this.uid = uid
    }

    override fun isValidToken(uid: String): Boolean {
        TODO("Not yet implemented")
    }
}