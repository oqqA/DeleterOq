package com.example.deleteroq.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import android.util.Log
import android.widget.Toast
import com.example.deleteroq.DeleterOqService

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("KEKW", "reciver")
        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent(context, DeleterOqService::class.java).also { indent ->
                Log.d("KEKW", "start boot reciver")
                context.startForegroundService(indent)
            }
        }
    }

}