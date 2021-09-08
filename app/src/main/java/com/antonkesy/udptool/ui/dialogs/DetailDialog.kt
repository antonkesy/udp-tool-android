package com.antonkesy.udptool.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.messages.ILogMessage

@Composable
fun DetailDialog(logMessage: ILogMessage, showDetailDialog: MutableState<Boolean>) {
    if (showDetailDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDetailDialog.value = false
            },
            title = {
                Text(logMessage.title)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDetailDialog.value = false
                    },
                ) {
                    Text("ok")
                }
            },
            text = {
                DetailDialogContent(logMessage)
            }
        )
    }
}

@Composable
fun DetailDialogContent(logMessage: ILogMessage) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(text = "Time: ")
            Text(text = logMessage.time)
        }
    }
}