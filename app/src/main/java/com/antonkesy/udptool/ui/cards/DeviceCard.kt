package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun IPConfigCardContent(
    label: String,
    modifier: Modifier,
    onLocalPortValueChanged: (text: String) -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        CardHeader(label)
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        var textValue by remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it; onLocalPortValueChanged(it.text) },
            label = { Text("Local Port") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

