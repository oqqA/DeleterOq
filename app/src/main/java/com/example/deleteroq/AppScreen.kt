package com.example.deleteroq

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deleteroq.model.Task
import com.example.deleteroq.ui.theme.DeleterOqTheme

@Composable
fun ScreenContent(
    tasks: List<Task>,
    task: Task,
    onEditTask: (Task) -> Unit
) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = task.path,
            onValueChange = { onEditTask(task.copy(path = it)) },
            label = { Text("Path") },
            modifier = Modifier.fillMaxWidth()
        )

//        Spacer(Modifier.size(8.dp))
//
//        TextField(
//            value = task.days_of_life.toString(),
//            onValueChange = { onEditTask(task.copy(days_of_life = it.toInt()) ) },
//            label = { Text("Days of life") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
//            keyboardActions = KeyboardActions(onDone = {}),
//            maxLines = 1,
//            modifier = Modifier.requiredWidth(130.dp)
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeleterOqTheme {
        ScreenContent(listOf<Task>(Task("/os/...",7)),Task("/kl/..",30),{})
    }
}