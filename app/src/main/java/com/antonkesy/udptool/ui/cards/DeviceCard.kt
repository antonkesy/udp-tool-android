package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.setNewLocalPort

@Composable
fun DeviceCard(logViewModel: MessageLogViewModel) {
    val label = "Device"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            val ip by logViewModel.localIP.observeAsState("0.0.0.0")
            DeviceCardContent(
                ip = ip, logViewModel
            )
        }
    )
}

@Composable
fun DeviceCardContent(
    ip: String,
    logViewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        Text("IP: $ip")
        NumberOutlinedTextField(
            label = "Local Port",
            isErrorOnOutlineTextFieldValueChange = { setNewLocalPort(it, logViewModel) },
            value = logViewModel.localPort.value.toString()
        )

    }
}

