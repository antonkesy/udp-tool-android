package com.antonkesy.udptool.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.CardHeader

@Composable
fun IPConfigCardContent(label: String, modifier: Modifier) {
    Column(modifier) {
        CardHeader(label)
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        Row {
            Text(text = "Local Port")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
    }
}

