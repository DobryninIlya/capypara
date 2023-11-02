package com.kai.capybara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.kai.capybara.R
import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group

class SharedPreferenceManager(context: Context) : LocalStorage {

    private val uidKey = context.getString(R.string.preference_uid_key)

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    override fun saveToken(uid: String) =
        sharedPreference.edit().putString(uidKey, uid).apply()

    override fun getUid(): String? {
        return sharedPreference.getString(uidKey, "")
    }

    override fun saveSchedule(schedule: Schedule) {
        TODO("Not yet implemented")
    }

    override fun getSchedule(): Schedule {

        return TODO("Provide the return value")
    }

    override fun saveGroupName(groupName: Int) {
        TODO("Not yet implemented")
    }

    override fun getGroup(): Group {
        TODO("Not yet implemented")
    }

}