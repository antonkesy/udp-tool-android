package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@Composable
fun RemoteContent(
    label: String,
    modifier: Modifier,
    onRemoteIPChange: (ip: String) -> Boolean,
    onRemotePortChange: (port: String) -> Boolean
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CardHeader(label)
        NumberOutlinedTextField("IP", onRemoteIPChange)
        NumberOutlinedTextField("Port", onRemotePortChange)
    }
}
