package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel

@Composable
fun ToggleLogCard(logViewModel: MessageLogViewModel) {
    val label = "Logging"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            var isLogging by remember { mutableStateOf(false) }
            ToggleLogCardContent(
                isLogging = isLogging,
                onLoggingToggleClick = { isLogging = !isLogging },
                viewModel = logViewModel
            )
        }
    )
}

@Composable
fun ToggleLogCardContent(
    isLogging: Boolean,
    onLoggingToggleClick: () -> Unit, viewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        SetBufferSize(viewModel)
        ToggleLoggingButton(isLogging = isLogging, onLoggingToggleClick = onLoggingToggleClick)
    }
}

@Composable
fun ToggleLoggingButton(isLogging: Boolean, onLoggingToggleClick: () -> Unit) {
    Button(onClick = { onLoggingToggleClick() }, Modifier.padding(top = 16.dp)) {
        if (isLogging) {
            Text(text = "stop logging")
        } else {
            Text(text = "start logging")
        }
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
