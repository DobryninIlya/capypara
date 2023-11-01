package com.kai.capybara.domain.model.schedule

import java.util.Date

class LessonDate() {

    var type: LessonDateType = LessonDateType.Unknown
    var isToday = true
    private var dateString = ""

    val asString: String
        get() = dateString


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


    private fun getDatesType(dateStr: String): LessonDateType {
        if (dateStr.contains("/")
            && dateStr.split("/").size == 2
        ) return LessonDateType.DatesForSubGroups

        return LessonDateType.Dates
    }

    private fun containDates(dateStr: String): Boolean =
        Regex("\\d{1,2}\\.(\\d{1,2})").matches(dateStr)

    private fun isNoInfo(string: String): Boolean {
        val dotsOrDashesRegex = Regex("^[.-]+[.\\s]+$")
        return dotsOrDashesRegex.matches(string)
    }

    fun makeEmpty() {
        dateString = ""
    }

    fun checkForParityMatch(date: Date) {
        
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
