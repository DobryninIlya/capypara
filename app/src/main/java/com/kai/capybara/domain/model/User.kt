package com.kai.capybara.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    var uid: String = "",
    var token: String = "",
    var device_tag: String = "device_tag",
    var groupName: Int = 0,
    var createDate: CreateDate? = CreateDate(),
    var faculty: String = "",
    var id_card: Int = 0,
    var name: String = "",

    )

@Serializable
data class CreateDate(

    var Time: String? = null,
    var Status: Int? = null,
    var InfinityModifier: Int? = null

)