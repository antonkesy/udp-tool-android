package com.antonkesy.udptool.ui.dialogs

import android.content.Intent
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SendAttachmentDialog(data: Intent?) {

}

@Composable
fun AttachmentDialogBox(
    dialogTitle: String,
    dialogText: String,
    onConfirm: () -> Unit
) {

    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(dialogTitle)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                },
            ) {
                Text("Ok :)")
            }
        },
        text = {
            Text(dialogText)
        },
    )
}

