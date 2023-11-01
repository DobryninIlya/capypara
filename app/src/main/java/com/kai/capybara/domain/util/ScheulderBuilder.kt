package com.kai.capybara.domain.util

import com.kai.capybara.domain.model.schedule.Lesson
import com.kai.capybara.domain.model.schedule.LessonDate
import com.kai.capybara.domain.model.schedule.LessonDateType
import com.kai.capybara.domain.model.schedule.Lessons
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Week
import java.util.Date


fun rebuildSchedule(schedule: Schedule, week: Week, currentDate: Date): Schedule {

    val rebuiltSchedule = Schedule()

    schedule.daysLessons.forEach { lessons: Lessons ->
        rebuiltSchedule.daysLessons.add(rebuildDayLessons(lessons, week.week_parity, currentDate))
    }

    return rebuiltSchedule
}




fun rebuildDayLessons(lessons: ArrayList<Lesson>, weekParity: Int, date: Date): ArrayList<Lesson> {

    val rebuiltDayLessons = ArrayList<Lesson>(lessons.size)

    lessons.forEach { lesson ->

        val rebuiltLesson = lesson.copy()

        val lessonDate = LessonDate(lesson.dayDate)

        if (lessonDate.type == LessonDateType.NoInfo) {
            lessonDate.makeEmpty()
        }
        else if (lessonDate.type == LessonDateType.WeekParity) {
            lessonDate.checkForParityMatch(date)
        } else if (lessonDate.type == LessonDateType.SubGroupWeekParity) {
            lesson.changeForSubGroupNumber(weekParity)
        } else if (lessonDate.type == LessonDateType.DatesForSubGroups) {
            lessonDate.changeForSubGroupNumber(date)
        } else if (lessonDate.type = LessonDateType.Dates) {
            lessonDate.checkForDate(date)
        }

        if (lessonDate.isToday) {
            rebuiltLesson.dayDate = lessonDate.asString
            rebuiltDayLessons.add(lesson)
        }
    }

    return rebuiltDayLessons
}

private fun reformatDayDate(dayDate: String, weekParity: Int, date: Date): String {
    val dayDate = dayDate.trim().lowercase()

    if (isNoInfo(dayDate)) return ""
    if (isWeekParityToFirstGroup(dayDate)) return "1group"
    if (isWeekParityToSecondGroup(dayDate)) return "2group"
    if (isCurrentDate(dayDate, date)) return getSubGroupForCurrentDate(dayDate, date)

    return dayDate

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