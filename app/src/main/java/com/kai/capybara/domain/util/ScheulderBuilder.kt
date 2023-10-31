package com.kai.capybara.domain.util

import com.kai.capybara.domain.model.schedule.EVEN
import com.kai.capybara.domain.model.schedule.EVEN_UNEVEN_STRING
import com.kai.capybara.domain.model.schedule.Lesson
import com.kai.capybara.domain.model.schedule.Lessons
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.UNEVEN
import com.kai.capybara.domain.model.schedule.UNEVEN_EVEN_STRING
import com.kai.capybara.domain.model.schedule.UNEVEN_STRING
import com.kai.capybara.domain.model.schedule.Week
import java.util.Date


fun rebuildSchedule(schedule: Schedule, week: Week, currentDate: Date): Schedule {

    val rebuiltSchedule = Schedule()

    schedule.daysLessons.forEach { lessons: Lessons ->
        rebuiltSchedule.daysLessons.add(rebuildDayLessons(lessons, week.week_parity, currentDate))
    }

    return rebuiltSchedule
}

fun isNoInfo(string: String): Boolean {
    val dotsOrDashesRegex = Regex("^[.-]+[.\\s]+$")
    return dotsOrDashesRegex.matches(string)
}


fun rebuildDayLessons(lessons: Lessons, weekParity: Int, date: Date): ArrayList<Lesson> {

    val rebuiltDayLessons = ArrayList<Lesson>(lessons.size)
    lessons.forEach { lesson ->
        val weekOrDatesString = lesson.dayDate.trim().lowercase()

        if (isNoInfo(lesson.audNum)) lesson.audNum = ""

        val (dateType1, dateType2) = getDate(weekOrDatesString, 0)

        val isContainDate = (dateType1 + dateType2 != "")

        val isNotNeededToReformat = isNoInfo(weekOrDatesString) ||
                isWeekParityMatch(weekOrDatesString, weekParity) ||
                !isContainDate

        if (isNotNeededToReformat) {
            rebuiltDayLessons.add(lesson)
            return@forEach
        }

        if (isWeekParityToFirstGroup(weekOrDatesString, weekParity)) {

            lesson.dayDate = "[1 гр.]"

        } else if (isWeekParityToSecondGroup(weekOrDatesString, weekParity)) {

            lesson.dayDate = "[2 гр.]"

        } else if (isContainDate) {

            lesson.dayDate = getSubgroupForDate(
                weekOrDatesString,
                dateType1,
                dateType2
            )
        }

        rebuiltDayLessons.add(lesson)
    }

    return rebuiltDayLessons
}


fun getSubgroupForDate(data: String, ex1: String, ex2: String): String {
    if (data.contains("/")) {
        val parts = data.split("/")
        if (parts.size != 2) {
            return data
        }
        if (parts[0].contains(ex1) || parts[0].contains(ex2)) {
            return "[1 гр.]"
        } else if (parts[1].contains(ex1) || parts[1].contains(ex2)) {
            return "[2 гр.]"
        }
    } else {
        if (data.contains(ex1) || data.contains(ex2)) {
            return ex1
        }
    }
    return ""
}

fun isContainsInDict(date: String): Boolean {
    for (v in reservedDict) {
        if (v == date) {
            return true
        }
    }
    return false
}


fun getDate(data: String, margin: Int): Pair<String, String> {
    val date = LocalDate.now().plusDays(margin.toLong())
    val day = date.dayOfMonth
    val dayString = day.toString()

    val month = date.monthValue
    var monthString = month.toString()
    if (month < 10) {
        monthString = "0$monthString"
    }

    val ex1 = "$dayString.$monthString"
    var ex2 = ex1
    if (day < 10) {
        ex2 = "0$dayString.$monthString"
    }

    if (data.contains(ex1) || data.contains(ex2)) {
        return Pair(ex1, ex2)
    }

    return Pair("", "")
}