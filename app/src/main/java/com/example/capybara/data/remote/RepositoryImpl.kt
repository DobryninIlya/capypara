package com.example.capybara.data.remote

import com.example.capybara.domain.model.FirebaseInterface
import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.Repository.UnavailableRepositoryException
import com.example.capybara.domain.model.User
import com.example.capybara.domain.model.schedule.Schedule
import com.example.capybara.domain.model.schedule.Group
import com.example.capybara.domain.model.schedule.Week
import okio.IOException
import java.net.SocketTimeoutException


class RepositoryImpl : Repository {


    class UidNotSetException : Exception()
    class NoInfoException : Exception()

    private var uid: String = ""

    private val api = CapyparaApi.get()

    private lateinit var firebase: FirebaseInterface

    override fun isValidgroupName(groupName: Int): Boolean {
        return try {
            getGroup(groupName)
            true
        } catch (e: NoInfoException) {
            false
        }
    }


    override fun getGroup(groupName: Int): Group {
        isUidSet()

        try {

            val group = api.getGroupId(groupName, uid).execute().body()?.result
                ?: throw NoInfoException()

            return Group(group.group_id, groupName)

        } catch (e: SocketTimeoutException) {

            throw UnavailableRepositoryException()

        }
    }

    override fun registerUser(user: User): String {

        user.uid = firebase.registerUser()
        val res = api.registerUser(user).execute()
        if (res.isSuccessful) {
            res.errorBody()
        }
        return "asd"
    }

    override fun getSchedule(group: Group): Schedule {

        isUidSet()

        return api.getSchedule(uid = uid, groupId = group.group_id).execute()
            .body()?.result?.schedule
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