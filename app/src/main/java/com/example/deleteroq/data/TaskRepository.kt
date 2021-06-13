package com.example.deleteroq.data

import com.example.deleteroq.model.Task

class TaskRepository(val db: TaskDatabase) {
    private val taskDao = db.taskDao()

    fun getTasks() = taskDao.getAllTasks()

    fun saveTasks(tasks: List<Task>) {
        taskDao.deleteAllTasks()
        taskDao.insertTasks(tasks)
    }
}