package com.example.deleteroq.model

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.room.Room
import com.example.deleteroq.data.TaskDatabase
import com.example.deleteroq.data.TaskRepository
import kotlinx.coroutines.flow.*

class TaskViewModel: ViewModel() {
    var taskItems by mutableStateOf(listOf<Task>())
        private set

    private lateinit var database: TaskDatabase

    fun init(db: TaskDatabase) {
        database = db
        taskItems = taskItems + TaskRepository(database).getTasks()
    }

    fun save() {
        TaskRepository(database).saveTasks(taskItems)
    }

    fun addTask(task: Task) {
        taskItems = taskItems + listOf(task)
        save()
    }

    fun removeTask(task: Task) {
        taskItems = taskItems.toMutableList().also {
            it.remove(task)
        }
        save()
    }

    fun editTask(taskA: Task, taskB: Task) {
        val currentEditPosition = taskItems.indexOf(taskA)
        if (currentEditPosition != -1) {
            taskItems = taskItems.toMutableList().also {
                it[currentEditPosition] = taskB
                save()
            }
        }
    }


}