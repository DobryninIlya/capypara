package com.example.capybara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.capybara.R
import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.schedule.DaySchedule

class SharedPreferenceManager(context: Context) : LocalStorage {

    private val uidKey = context.getString(R.string.preference_uid_key)

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    override fun saveUid(uid: String) =
        sharedPreference.edit().putString(uidKey, uid).apply()

    override fun getUid(): String? {
        return sharedPreference.getString(uidKey, "")
    }

    override fun saveSchedule(schedule: List<DaySchedule>) {
        TODO("Not yet implemented")
    }

    override fun getSchedule(): List<DaySchedule> {

        return TODO("Provide the return value")
    }

    override fun saveGroupNumber(uid: String) {
        TODO("Not yet implemented")
    }

    override fun getGroupNumber(): Int {
        TODO("Not yet implemented")
    }

}