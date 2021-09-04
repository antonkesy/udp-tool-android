package com.antonkesy.udptool.ui.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ClearLogDialogBox(
    isOpen: Boolean,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    if (isOpen) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text("Clear logs?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                    },
                ) {
                    Text("Clear logs!")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismissRequest()
                    },
                ) {
                    Text("NO!")
                }
            },
            text = {
                Text("you really want to reset the logs?")
            },
        )
    }
}

