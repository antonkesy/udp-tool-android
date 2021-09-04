package com.antonkesy.udptool.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun NumberOutlinedTextField(
    label: String,
    isErrorOnOutlineTextFieldValueChange: (text: String) -> Boolean,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isActive: Boolean = true,
    value: String = ""
) {
    var textValuePort by remember { mutableStateOf(TextFieldValue(value)) }
    var isErrorInput by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = textValuePort,
        onValueChange = {
            textValuePort = it;isErrorInput = isErrorOnOutlineTextFieldValueChange(it.text)
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

@Composable
fun MessageSendTextFieldRow(
    onSendAttachmentClick: () -> Unit,
    onSendMessageClick: (message: String) -> Unit
) {
    var sendText by remember { mutableStateOf("") }
    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = sendText, onValueChange = { sendText = it },
            Modifier.weight(1f), shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                //todo
                /*
                if (sendText.isEmpty()) {
                    IconButton(onClick = { onSendAttachmentClick() }) {
                        Icon(
                            imageVector = Icons.Outlined.AttachFile,
                            contentDescription = null
                        )
                    }
                }*/
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        IconButton(
            onClick = { onSendMessageClick(sendText) },
            Modifier.wrapContentWidth()
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "send")
        }
    }
}