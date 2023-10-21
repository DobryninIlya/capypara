package com.example.capybara.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    var uid: String? = null,
    var token: String? = null,
    var device_tag: String? = null,
    val groupName: Int = 0,
    var createDate: CreateDate? = CreateDate()

)

@Serializable
data class CreateDate(

    @SerialName("Time") var Time: String? = null,
    @SerialName("Status") var Status: Int? = null,
    @SerialName("InfinityModifier") var InfinityModifier: Int? = null

)