package com.kai.capybara.data.local

import com.kai.capybara.domain.model.Response

class LocalDataSource(private val sharedPreferenceManager: SharedPreferenceManager) {
    fun getString(key: String): String? {
        return sharedPreferenceManager.getString(key)
    }

    fun putString(key: String, value: String) {
        sharedPreferenceManager.putString(key, value)
    }

    fun getInt(key: String): Int? =
        when (val result = sharedPreferenceManager.getInt(key)) {
            -1 -> null
            else -> result
        }

    fun putInt(key: String, value: Int) {
        sharedPreferenceManager.putInt(key, value)
    }

}