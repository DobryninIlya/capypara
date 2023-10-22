package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Lesson(
    var dayDate: String? = null,
    var audNum: String? = null,
    var disciplName: String? = null,
    var buildNum: String? = null,
    var orgUnitName: String? = null,
    var dayTime: String? = null,
    var dayNum: String? = null,
    var potok: String? = null,
    var prepodName: String? = null,
    var disciplNum: String? = null,
    var orgUnitId: String? = null,
    var prepodLogin: String? = null,
    var disciplType: String? = null

)
