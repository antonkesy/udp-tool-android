package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@Composable
fun RemoteContent(
    onRemoteIPChange: (ip: String) -> Boolean,
    onRemotePortChange: (port: String) -> Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        NumberOutlinedTextField("IP", onRemoteIPChange)
        NumberOutlinedTextField("Port", onRemotePortChange)
    }
}
