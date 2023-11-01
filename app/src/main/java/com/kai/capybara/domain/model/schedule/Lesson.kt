package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.Serializable


@Serializable
data class Lesson(
    var dayDate: String = "",
    var audNum: String = "",
    var disciplName: String = "",
    var buildNum: String = "",
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



