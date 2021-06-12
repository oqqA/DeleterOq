package com.example.deleteroq.model

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    var taskItems by mutableStateOf(listOf<Task>())
        private set

    fun init() {

    }

    fun save() {

    }

    fun addTask(task: Task) {
        taskItems = taskItems + listOf(task)
    }

    fun removeTask(task: Task) {
        taskItems = taskItems.toMutableList().also {
            it.remove(task)
        }
    }

    fun editTask(taskA: Task, taskB: Task) {
        val currentEditPosition = taskItems.indexOf(taskA)
        if (currentEditPosition != -1) {
            taskItems = taskItems.toMutableList().also {
                it[currentEditPosition] = taskB
            }
        }
    }


}