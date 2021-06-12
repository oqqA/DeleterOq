package com.example.deleteroq

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

class DeleterOqActivity : ComponentActivity() {

    val taskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskViewModel.addTask(Task("./kek/",228))
        taskViewModel.addTask(Task("./lawda.wd/",25))
        taskViewModel.addTask(Task("./keesatask/",7))

        setContent {
            DeleterOqTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "DeleterOq")
                            },
                            actions = {
                                var isPlay by remember { mutableStateOf(false) }
                                IconButton(onClick = { isPlay = !isPlay }) {
                                    Icon(
                                        if (isPlay) Icons.Filled.Close else Icons.Filled.PlayArrow,
                                        contentDescription = null
                                    )
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
}