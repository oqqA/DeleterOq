package com.example.deleteroq.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(
    val path: String,
    val days_of_life: Int? = 0,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
