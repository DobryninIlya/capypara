package com.kai.capybara.data.local

import com.kai.capybara.domain.model.api.Token
import com.kai.capybara.domain.model.schedule.Group

class UserLocal(val local: LocalDataSource) {

    fun getGroup() = Group(
        group_id = local.getInt(GROUP_KEY_ID),
        groupName = local.getInt(GROUP_KEY_NAME)
    )


    fun saveGroup(group: Group) {
        local.putInt(GROUP_KEY_NAME, group.groupName!!)
        local.putInt(GROUP_KEY_ID, group.group_id!!)
    }


    fun saveToken(uid: Token) {
        local.putString(UID_KEY, uid.token)
    }

    fun getToken() = local.getString(UID_KEY)

    fun getUid() = local.getString(UID_KEY)


    companion object {
        const val GROUP_KEY_NAME = "GROUP_NAME"
        const val GROUP_KEY_ID = "GROUP_ID"
        const val UID_KEY = "UID"

    }
}