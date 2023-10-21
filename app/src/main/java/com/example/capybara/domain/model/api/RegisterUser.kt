package com.example.capybara.domain.model.api

import kotlinx.serialization.Serializable


@Serializable
data class RegisterUser(
    val device_tag: String,
    val faculty: String? = null,
    val groupname: Int? = null,
    val id_card: Int? = null,
    val name: String = "",
    val uid: String
)