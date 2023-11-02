package com.kai.capybara.domain.util

import com.kai.capybara.domain.model.DateTime
import com.kai.capybara.domain.model.schedule.Lesson
import com.kai.capybara.domain.model.schedule.LessonDate
import com.kai.capybara.domain.model.schedule.LessonDateType
import com.kai.capybara.domain.model.schedule.Schedule
import com.kai.capybara.domain.model.schedule.Week


fun rebuildSchedule(schedule: Schedule, week: Week, currentDate: DateTime): Schedule {

    val rebuiltDayLessons: ArrayList<ArrayList<Lesson>> = ArrayList(6)

    schedule.daysLessons.forEachIndexed { i, lessons ->
        rebuiltDayLessons[i] = rebuildDayLessons(lessons, week.week_parity, currentDate)
    }

    return Schedule(rebuiltDayLessons)
}


fun rebuildDayLessons(
    lessons: ArrayList<Lesson>,
    weekParity: Int,
    date: DateTime
): ArrayList<Lesson> {

    val rebuiltDayLessons = ArrayList<Lesson>(lessons.size)

    lessons.forEach { lesson ->

        val rebuiltLesson = getClearLesson(lesson)

        val lessonDate = LessonDate(lesson.dayDate)

        when (lessonDate.type) {
            LessonDateType.NoInfo ->
                lessonDate.makeEmpty()

            LessonDateType.WeekParity ->
                lessonDate.checkForParityMatch(weekParity)

            LessonDateType.SubGroupWeekParity ->
                lessonDate.changeForSubGroupNumber(weekParity)

            LessonDateType.DatesForSubGroups ->
                lessonDate.changeForSubGroupNumber(date)

            LessonDateType.Dates ->
                lessonDate.checkForDate(date)

            else -> {}
        }

        if (lessonDate.isToday) {
            rebuiltLesson.dayDate = lessonDate.getString()
            rebuiltDayLessons.add(rebuiltLesson)
        }
    }

    return rebuiltDayLessons
}

fun getClearLesson(lesson: Lesson): Lesson {
    val newLesson = lesson.copy()

    if (LessonDate.isNoInfo(newLesson.audNum)) {
        newLesson.audNum = ""
    }

    return newLesson
}

