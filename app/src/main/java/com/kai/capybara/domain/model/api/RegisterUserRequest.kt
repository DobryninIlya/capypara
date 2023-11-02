package com.kai.capybara.domain.model.api

import com.kai.capybara.domain.model.User
import kotlinx.serialization.Serializable


@Serializable
data class RegisterUserRequest(
    val device_tag: String = "",
    val faculty: String = "",
    val groupname: Int = 4207,
    val id_card: Int = 0,
    val name: String = "",
    val uid: String = ""
) {
    constructor(user: User) : this(
        device_tag = user.device_tag,
        faculty = user.faculty,
        groupname = user.groupName,
        id_card = user.id_card,
        name = user.name,
        uid = user.uid,
    )
}
