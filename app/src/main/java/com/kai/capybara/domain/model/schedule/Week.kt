package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val EVEN = 0
const val UNEVEN = 1
const val EVEN_STRING = "чет"
const val UNEVEN_STRING = "неч"
const val UNEVEN_EVEN_STRING = "$UNEVEN_STRING/$EVEN_STRING"
const val EVEN_UNEVEN_STRING = "$EVEN_STRING/$UNEVEN_STRING"
const val FIRST_GROUP_STRING = "1group"
const val SECOND_GROUP_STRING = "2group"

@Serializable
class Week(val week_parity: Int) {
}
