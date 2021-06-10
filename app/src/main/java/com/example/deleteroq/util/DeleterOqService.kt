package com.example.deleteroq

import android.app.Service
import android.content.Intent
import android.os.IBinder

class DeleterOqService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}