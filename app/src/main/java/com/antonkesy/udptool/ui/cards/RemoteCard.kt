package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun RemoteContent(
    label: String,
    modifier: Modifier,
    onRemoteIPChange: (ip: String) -> Unit,
    onRemotePortChange: (port: String) -> Unit
) {
    Column(modifier = modifier) {
        CardHeader(label)
        var textValueIP by remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = textValueIP,
            onValueChange = { textValueIP = it; onRemoteIPChange(it.text) },
            label = { Text("IP") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        var textValuePort by remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = textValuePort,
            onValueChange = { textValuePort = it; onRemotePortChange(it.text) },
            label = { Text("Port") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
