package com.antonkesy.udptool.ui.dialogs

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun HelpDialogBoxButton(
    dialogTitle: String,
    dialogText: String
) {
    var isDialogShown by remember { mutableStateOf(false) }
    IconButton(
        onClick = { isDialogShown = true },
    ) {
        Icon(
            Icons.Filled.Help,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
    if (isDialogShown) {
        HelpDialogBox(
            dialogTitle = dialogTitle,
            dialogText = dialogText,
            onConfirm = { isDialogShown = false },
            onDismissRequest = { isDialogShown = false })
    }
}

@Composable
fun HelpDialogBox(
    dialogTitle: String,
    dialogText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
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

