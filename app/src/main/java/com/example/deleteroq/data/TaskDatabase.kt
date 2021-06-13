package com.example.deleteroq.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deleteroq.model.Task

@Database(entities = [Task::class], version = 2)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}