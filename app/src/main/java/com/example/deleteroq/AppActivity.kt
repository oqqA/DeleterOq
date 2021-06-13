package com.example.deleteroq

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deleteroq.model.Task
import com.example.deleteroq.model.TaskViewModel
import com.example.deleteroq.ui.theme.DeleterOqTheme
import com.example.deleteroq.util.Deleter
import androidx.core.content.ContextCompat.getSystemService

import android.app.ActivityManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.deleteroq.data.TaskDatabase


class DeleterOqActivity : ComponentActivity() {

    val taskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, TaskDatabase::class.java, "tasks").allowMainThreadQueries().build()
        taskViewModel.init(db)

        setContent {
            DeleterOqTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "DeleterOq")
                            },
                            actions = {
                                var isPlay by remember { mutableStateOf( (this@DeleterOqActivity).isMyServiceRunning(DeleterOqService::class.java) ) }
                                IconButton(onClick = { isPlay = !isPlay }) {
                                    Icon(
                                        if (isPlay) Icons.Filled.Close else Icons.Filled.PlayArrow,
                                        contentDescription = null
                                    )
                                }

                                Intent(this@DeleterOqActivity, DeleterOqService::class.java).also { indent ->
                                    if (isPlay) {
                                        startForegroundService(indent)
                                    } else {
                                        stopService(indent)
                                    }
                                }

                                var isDialogEdit by remember { mutableStateOf(false) }
                                DialogEdit(
                                    isDialogEdit,
                                    {isDialogEdit = false},
                                    taskViewModel::addTask,
                                    taskViewModel::removeTask,
                                    taskViewModel::editTask,
                                    null
                                )
                                IconButton(onClick = { isDialogEdit = true }) {
                                    Icon(
                                        Icons.Filled.AddCircle,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) {
                    ScreenContent(
                        taskViewModel.taskItems,
                        taskViewModel::editTask,
                        taskViewModel::addTask,
                        taskViewModel::removeTask
                    )
                }
            }
        }
    }

    fun Context.isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return manager.getRunningServices(Integer.MAX_VALUE)
            .any { it.service.className == serviceClass.name }
    }
}

