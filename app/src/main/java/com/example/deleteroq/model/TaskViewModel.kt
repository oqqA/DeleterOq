package com.example.deleteroq.model

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
    var taskItems by mutableStateOf(listOf<Task>())
        private set

    val currentEditTask: Task
        get(){
            return taskItems.last()
        }

    fun test(): Task {
        return currentEditTask
    }

    fun addTask(task: Task) {
        taskItems = taskItems + listOf(task)
    }

    fun onEditTask(task: Task) {
        taskItems.toMutableList().also {
           it[0] = task
        }
    }

}