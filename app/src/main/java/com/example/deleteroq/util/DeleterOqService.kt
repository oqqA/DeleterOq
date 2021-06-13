package com.example.deleteroq

import android.app.Notification
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.app.NotificationCompat

import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build
import androidx.room.Room
import com.example.deleteroq.data.TaskDatabase
import com.example.deleteroq.data.TaskRepository
import com.example.deleteroq.util.Deleter

class DeleterOqService: Service() {
    override fun onCreate() {
        Log.d("KEKW", "start service")

        val notification: Notification = NotificationCompat.Builder(this, "")
            .setContentTitle("")
            .setContentText("").build()
        startForeground(2, notification)
    }

    override fun onDestroy() {
        Log.d("KEKW", "destroy service")
        isServiceStarted = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isServiceStarted) return START_STICKY
        isServiceStarted = true
        Log.d("KEKW", "start service command")
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        val database = Room.databaseBuilder(this, TaskDatabase::class.java, "tasks").allowMainThreadQueries().build()
        val taskRepos = TaskRepository(database)

        GlobalScope.launch(Dispatchers.IO) {
            while (isServiceStarted) {
                launch(Dispatchers.IO) {
                    Log.d("KEKW", "kek loop")
                    val tasks = taskRepos.getTasks()
                    tasks.forEach {
                        Deleter.run(it.path, it.days_of_life?:0)
                    }
                }
                delay( 24*60*60 * 1000)
            }
            Log.d("KEKW", "stop service loop")
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        var isServiceStarted = false
    }
}