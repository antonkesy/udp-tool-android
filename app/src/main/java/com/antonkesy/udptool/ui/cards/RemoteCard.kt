package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RemoteContent(label: String, modifier: Modifier) {
    Column(modifier = modifier) {
        CardHeader(label)
        Row {
            Text(text = "IP")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
        Row {
            Text(text = "Port")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
    }
}
