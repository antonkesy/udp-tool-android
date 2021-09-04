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
    isErroronOutlineTextFieldValueChange: (text: String) -> Boolean,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isActive: Boolean = true,
    value: String = ""
) {
    var textValuePort by remember { mutableStateOf(TextFieldValue(value)) }
    var isErrorInput by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = textValuePort,
        onValueChange = {
            textValuePort = it;isErrorInput = isErroronOutlineTextFieldValueChange(it.text)
        },
        label = { Text(label) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier, trailingIcon = {
            if (isErrorInput)
                Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colors.error)
        }, enabled = isActive
    )
}