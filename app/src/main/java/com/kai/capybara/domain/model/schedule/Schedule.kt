package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class ScheduleResult(
    val schedule: Schedule
)
@Serializable
data class Schedule(
    @SerialName("1") var monday: ArrayList<Lesson> = arrayListOf(),
    @SerialName("2") var tuesday: ArrayList<Lesson> = arrayListOf(),
    @SerialName("3") var wednesday: ArrayList<Lesson> = arrayListOf(),
    @SerialName("4") var thursday: ArrayList<Lesson> = arrayListOf(),
    @SerialName("5") var friday: ArrayList<Lesson> = arrayListOf(),
    @SerialName("6") var saturday: ArrayList<Lesson> = arrayListOf(),
) {
    val daysLessons = arrayListOf(monday, tuesday, wednesday, thursday, friday, saturday)
}
