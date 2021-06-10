package com.example.deleteroq.model

import java.util.*

data class Task(
    val path: String,
    val days_of_life: Int,

    val id: UUID = UUID.randomUUID()
)
