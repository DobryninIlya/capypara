package com.example.capybara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.capybara.R
import com.example.capybara.domain.model.schedule.DaySchedule

class SharedPreferenceManager(context: Context) {

    private val uidKey = context.getString(R.string.preference_uid_key)

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    class EmptyValueException: Exception()

    fun saveUid(uid: String) =
        sharedPreference.edit().putString(uidKey, uid).apply()

    fun getUid(): String? {
        return sharedPreference.getString(uidKey, "")
    }

    fun saveSchedule(schedule: List<DaySchedule>) {
        TODO("Not yet implemented")
    }

    fun getSchedule(): List<DaySchedule> {

        return TODO("Provide the return value")
    }

}