package com.example.deleteroq.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deleteroq.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTasks(tasks: List<Task>)

    @Query("DELETE FROM tasks")
    fun deleteAllTasks()
}