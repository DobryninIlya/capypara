package com.kai.capybara.domain.model.schedule

import com.kai.capybara.domain.model.DateTime

class LessonDate() {

    var type: LessonDateType = LessonDateType.Unknown

    var isToday = true

    private var dateString = ""

    fun getString() = dateString

    constructor(
        dayDate: String,
    ) : this() {
        setDateString(dayDate)
        type = setType()
    }

    private fun setDateString(dayDate: String) {
        dateString = dayDate.trim().lowercase()
    }

    private fun setType(): LessonDateType =
        if (isNoInfo(dateString)) {
            LessonDateType.NoInfo
        } else if (dateString == EVEN_STRING || dateString == UNEVEN_STRING) {
            LessonDateType.WeekParity
        } else if (dateString == EVEN_UNEVEN_STRING || dateString == UNEVEN_EVEN_STRING) {
            LessonDateType.SubGroupWeekParity
        } else if (containDates(dateString)) {
            getDatesType(dateString)
        } else {
            LessonDateType.Unknown
        }


    private fun getDatesType(dateStr: String): LessonDateType =
        if (
            dateStr.contains("/") &&
            dateStr.split("/").size == 2
        )
            LessonDateType.DatesForSubGroups
        else {
            LessonDateType.Dates
        }


    fun makeEmpty() {
        dateString = ""
    }

    fun checkForParityMatch(weekParity: Int) {
        if (
            (weekParity == EVEN && dateString != EVEN_STRING) ||
            (weekParity == UNEVEN && dateString != UNEVEN_STRING)
        ) {
            isToday = false
        }
    }


    fun changeForSubGroupNumber(weekParity: Int) {
        dateString = if (dateString == EVEN_UNEVEN_STRING && weekParity == EVEN) {
            FIRST_GROUP_STRING
        } else {
            SECOND_GROUP_STRING
        }
    }

    fun changeForSubGroupNumber(date: DateTime) {
        val (date1, date2) = dateString.split("/")

        dateString = if (
            date1.contains(date.dayMonthStringWithoutZero) ||
            date1.contains(date.dayMonthString)
        ) {
            FIRST_GROUP_STRING
        } else if (
            date2.contains(date.dayMonthStringWithoutZero) ||
            date2.contains(date.dayMonthString)
        ) {
            SECOND_GROUP_STRING
        } else {
            ""
        }


    }

    fun checkForDate(date: DateTime) {
        if (
            dateString.contains(date.dayMonthString) ||
            dateString.contains(date.dayMonthStringWithoutZero)
        ) {
            dateString = date.dayMonthString
        } else {
            isToday = false
        }
    }


    companion object {
        fun containDates(dateStr: String): Boolean =
            Regex("\\d{1,2}\\.(\\d{1,2})").matches(dateStr)

        fun isNoInfo(string: String): Boolean {
            val dotsOrDashesRegex = Regex("^[.-]+[.\\s]+$")
            return dotsOrDashesRegex.matches(string)
        }

    }


}


enum class LessonDateType {
    WeekParity,
    SubGroupWeekParity,
    DatesForSubGroups,
    Dates,
    Unknown,
    NoInfo
}
