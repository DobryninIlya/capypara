package com.kai.capybara.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    var uid: String? = null,
    var token: String? = null,
    var device_tag: String? = "device_tag",
    val groupName: Int = 0,
    var createDate: CreateDate? = CreateDate(),
    var faculty: String? = null,
    val id_card: Int? = null,
    val name: String? = null,

    )

@Serializable
data class CreateDate(

    @SerialName("Time") var Time: String? = null,
    @SerialName("Status") var Status: Int? = null,
    @SerialName("InfinityModifier") var InfinityModifier: Int? = null

)