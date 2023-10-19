package com.example.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Lesson(
    @SerialName("dayDate") var dayDate: String? = null,
    @SerialName("audNum") var audNum: String? = null,
    @SerialName("disciplName") var disciplName: String? = null,
    @SerialName("buildNum") var buildNum: String? = null,
    @SerialName("orgUnitName") var orgUnitName: String? = null,
    @SerialName("dayTime") var dayTime: String? = null,
    @SerialName("dayNum") var dayNum: String? = null,
    @SerialName("potok") var potok: String? = null,
    @SerialName("prepodName") var prepodName: String? = null,
    @SerialName("disciplNum") var disciplNum: String? = null,
    @SerialName("orgUnitId") var orgUnitId: String? = null,
    @SerialName("prepodLogin") var prepodLogin: String? = null,
    @SerialName("disciplType") var disciplType: String? = null

)
