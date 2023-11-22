package com.kai.capybara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.kai.capybara.R
import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Group

class SharedPreferenceManager(context: Context) : LocalStorage {

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )


    override fun putString(key: String, value: String) =
        sharedPreference.edit().putString(key, value).apply()


    override fun getString(key: String): String? =
        sharedPreference.getString(key, null)


    override fun putInt(key: String, value: Int) =
        sharedPreference.edit().putInt(key, value).apply()

    override fun getInt(key: String): Int = sharedPreference.getInt(key, -1)
}