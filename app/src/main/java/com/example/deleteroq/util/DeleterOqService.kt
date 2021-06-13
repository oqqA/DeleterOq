package com.example.deleteroq

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

class DeleterOqService: Service() {
    override fun onCreate() {
        Log.d("KEKW", "start service")
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

        GlobalScope.launch(Dispatchers.IO) {
            while (isServiceStarted) {
                launch(Dispatchers.IO) {
                    Log.d("KEKW", "kek loop")
                }
                delay( 10 * 1000)
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