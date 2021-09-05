package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.dialogs.ClearLogDialogBox
import com.antonkesy.udptool.ui.log.MessageLogViewModel

@Composable
fun LogInfoCard(logViewModel: MessageLogViewModel) {
    val label = "Logging"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            LogInfoCardContent(
                viewModel = logViewModel
            )
        }
    )
}

@Composable
fun LogInfoCardContent(
    viewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        SetBufferSize(viewModel)
        ClearLogButton(viewModel = viewModel)
    }
}


@Composable
fun SetBufferSize(viewModel: MessageLogViewModel) {
    NumberOutlinedTextField(
        label = "buffer size (byte)",
        value = viewModel.bufferSize.value.toString(),
        isErrorOnOutlineTextFieldValueChange = {
            if (isStringLegalBufferSize(it)) {
                viewModel.setBufferSize(Integer.parseInt(it))
                return@NumberOutlinedTextField false
            } else {
                return@NumberOutlinedTextField true
            }
        })
}

fun isStringLegalBufferSize(input: String): Boolean {
    try {
        if (Integer.parseInt(input) in 1..Int.MAX_VALUE) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false;
}

@Composable
fun ClearLogButton(viewModel: MessageLogViewModel) {
    var isOpen by remember { mutableStateOf(false) }
    Button(onClick = { isOpen = true }) {
        Text("Clear log")
    }
    ClearLogDialogBox(
        isOpen = isOpen,
        onConfirm = {
            viewModel.setLogMessages(mutableListOf())
            isOpen = false
        },
        onDismissRequest = { isOpen = false })
}