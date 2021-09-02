package com.antonkesy.udptool.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun NumberOutlinedTextField(
    label: String,
    onOutlineTextFieldValueChange: (text: String) -> Boolean
) {
    var textValuePort by remember { mutableStateOf(TextFieldValue()) }
    var isErrorPort by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = textValuePort,
        onValueChange = { textValuePort = it;isErrorPort = onOutlineTextFieldValueChange(it.text) },
        label = { Text(label) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(), trailingIcon = {
            if (isErrorPort)
                Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colors.error)
        }
    )
}