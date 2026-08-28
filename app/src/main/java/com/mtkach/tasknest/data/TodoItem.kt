package com.mtkach.tasknest.data

import java.util.UUID

data class TodoItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val done: Boolean = false
)
