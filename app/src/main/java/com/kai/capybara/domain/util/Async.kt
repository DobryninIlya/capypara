package com.kai.capybara.domain.util

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks

fun <T> Task<T>.await(): Task<T> {
    Tasks.await(this)
    return this
}