package com.kai.capybara.domain.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


const val EVEN = 0
const val UNEVEN = 0
@Serializable
class Week(val week_parity: Int)
