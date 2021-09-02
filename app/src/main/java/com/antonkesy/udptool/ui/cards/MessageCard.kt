package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@Composable
fun MessagesCardContent(
    label: String,
    modifier: Modifier,
    onTimeoutToggle: (isEnabled: Boolean) -> Unit,
    onTimeoutChange: (text: String) -> Boolean
) {
    Column(modifier = modifier) {
        CardHeader(label)
        var isTimeoutEnabled by remember { mutableStateOf(true) }
        Row {
            Text("Timeout")
            Switch(
                checked = isTimeoutEnabled,
                onCheckedChange = { isTimeoutEnabled = it;onTimeoutToggle(it) })
            NumberOutlinedTextField("Timeout", onTimeoutChange)
        }
        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
}

