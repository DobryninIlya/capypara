package com.kai.capybara.domain.model.api

import com.kai.capybara.domain.model.User
import kotlinx.serialization.Serializable


@Serializable
data class RegisterUserRequest(
    val device_tag: String,
    val faculty: String? = null,
    val groupname: Int? = null,
    val id_card: Int? = null,
    val name: String? = null,
    val uid: String
) {
    constructor(user: User) : this(
        device_tag = user.device_tag!!,
        faculty = user.faculty,
        groupname = user.groupName,
        id_card = user.id_card,
        name = user.name,
        uid = user.uid!!,
    )
}