package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class ScheduleResult(
    val schedule: Schedule
)
@Serializable
data class Schedule(
    @SerialName("1") var monday: Lessons = arrayListOf(),
    @SerialName("2") var tuesday: Lessons = arrayListOf(),
    @SerialName("3") var wednesday: Lessons = arrayListOf(),
    @SerialName("4") var thursday: Lessons = arrayListOf(),
    @SerialName("5") var friday: Lessons = arrayListOf(),
    @SerialName("6") var saturday: Lessons = arrayListOf(),
) {
    val daysLessons = arrayListOf(monday, tuesday, wednesday, thursday, friday, saturday)
}
