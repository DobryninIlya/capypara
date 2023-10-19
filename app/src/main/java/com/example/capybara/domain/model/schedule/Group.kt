package com.example.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(@SerialName("group_id")val groupId: Int = 0, val groupNumber: Int = 0)