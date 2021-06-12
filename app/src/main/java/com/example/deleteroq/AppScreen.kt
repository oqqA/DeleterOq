package com.example.deleteroq

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.deleteroq.model.Task
import com.example.deleteroq.ui.theme.DeleterOqTheme
import kotlin.math.roundToInt

@Composable
fun ScreenContent(
    tasks: List<Task>,
    editTask: (Task,Task) -> Unit,
    addTask: (Task) -> Unit,
    removeTask: (Task) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(items = tasks) { task ->
            var isDialogEdit by remember { mutableStateOf(false) }
            DialogEdit(isDialogEdit, {isDialogEdit = false}, addTask, removeTask, editTask, task )

            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.surface)
                    .clickable(onClick = { isDialogEdit = true })
                    .padding(8.dp)
            ) {
                Column (
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(task.days_of_life.toString()+" days", fontWeight = FontWeight.Bold)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(task.path, style = MaterialTheme.typography.body2)
                    }

//                    Box(
//                        Modifier
//                            .fillMaxWidth(data.relative_size)
//                            .height(4.dp)
//                            .clip(RoundedCornerShape(2.dp))
//                            .background(color = Color.Green.copy(alpha = 0.6f))
//                    )
                }
            }

            Divider(
                color = Color.Gray.copy(alpha = 0.1f),
                //modifier = Modifier.padding(start = 75.dp)
            )

        }
    }


}

@Composable
fun DialogEdit(
    isDialogEdit: Boolean,
    closeDialog: () -> Unit,
    addTask: (Task) -> Unit,
    removeTask: (Task) -> Unit?,
    editTask: (Task,Task) -> Unit?,
    task: Task?
) {

    if (isDialogEdit) {
        val (path, onPathChange) = rememberSaveable { mutableStateOf( if (task!=null) task.path else "" ) }
        val (days_of_life, onDaysOfTimeChange) = rememberSaveable { mutableStateOf(if (task!=null) task.days_of_life.toString() else "") }
        AlertDialog(
            onDismissRequest = {
                closeDialog()
            },
            title = {
                Text(text = "Editing task")
            },
            text = {
                Text("")
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {

                    OutlinedTextField(
                        value = path,
                        onValueChange = onPathChange ,
                        label = { Text("Path") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                    )

                    Spacer(Modifier.size(8.dp))

                    OutlinedTextField(
                        value = days_of_life,
                        onValueChange = onDaysOfTimeChange,
                        label = { Text("Days of life") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {}),
                        singleLine = true,
                        modifier = Modifier.requiredWidth(130.dp).height(60.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (task == null) {
                            addTask(Task(path, days_of_life.toIntOrNull()?:0))
                        } else {
                            editTask(task,Task(path, days_of_life.toIntOrNull()?:0))
                        }

                        closeDialog()
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        if (task != null) {
                            removeTask(task)
                        }
                        closeDialog()
                    }
                ) {
                    Text("Delete")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeleterOqTheme {
        //ScreenContent(listOf<Task>(Task("/os/...",7)),Task("/kl/..",30),{},false,{})
    }
}