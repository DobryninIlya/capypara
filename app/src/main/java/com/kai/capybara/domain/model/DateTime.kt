package com.kai.capybara.domain.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class DateTime(
    val month: Int,
    val day: Int,
    val dayMonthString: String,
    val dayMonthStringWithoutZero: String
) {
    constructor(calendar: Calendar) : this(
        month = calendar.get(Calendar.MONTH),
        day = calendar.get(Calendar.DAY_OF_MONTH),
        dayMonthString = SimpleDateFormat("dd.MM", Locale.getDefault()).format(calendar.time),
        dayMonthStringWithoutZero =
        SimpleDateFormat(
            "d.MM", Locale.getDefault()
        ).format(calendar.time)
    )
}

