package com.antonkesy.udptool.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MessagesCardContent(label: String, modifier: Modifier) {
    Column(modifier = modifier) {
        CardHeader(label)
        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
}
