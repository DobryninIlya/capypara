package com.kai.capybara.data.local

import com.kai.capybara.data.JsonConverter
import com.kai.capybara.domain.model.schedule.Schedule

class ScheduleLocal(val local: LocalDataSource) {

    fun save(schedule: Schedule) {
        local.putString(SCHEDULE_KEY, JsonConverter.fromClassToString(schedule))
    }

    fun get(): Schedule? {
        val string = local.getString(SCHEDULE_KEY)
        if (string.isNullOrEmpty()) return null
        return JsonConverter.convertFromString<Schedule>(string)
    }



    companion object {
        private const val SCHEDULE_KEY = "SCHEDULE"
    }


}