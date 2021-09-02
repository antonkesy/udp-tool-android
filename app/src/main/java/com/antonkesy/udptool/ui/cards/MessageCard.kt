package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun MessagesCardContent(
    label: String,
    modifier: Modifier,
    onTimeoutToggle: (isEnabled: Boolean) -> Unit,
    onTimeoutChange: (text: String) -> Unit
) {
    Column(modifier = modifier) {
        CardHeader(label)
        var textValueTimeout by remember { mutableStateOf(TextFieldValue()) }
        var isTimeoutEnabled by remember { mutableStateOf(true) }
        Row {
            Text("Timeout")
            Switch(
                checked = isTimeoutEnabled,
                onCheckedChange = { isTimeoutEnabled = it;onTimeoutToggle(it) })
            OutlinedTextField(
                value = textValueTimeout,
                onValueChange = { textValueTimeout = it; onTimeoutChange(it.text) },
                label = { Text("Timeout") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
}

