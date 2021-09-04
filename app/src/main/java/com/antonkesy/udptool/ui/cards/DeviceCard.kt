package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.udp.setNewLocalPort
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel

data class DeviceInfo(val ip: String, val gateway: String, val networkType: String)

@Composable
fun DeviceCard(info: DeviceInfo, logViewModel: MessageLogViewModel) {
    val label = "Device"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            DeviceCardContent(
                ip = info.ip, gateway = info.gateway, networkType = info.networkType, logViewModel
            )
        }
    )
}

@Composable
fun DeviceCardContent(
    ip: String, gateway: String, networkType: String,
    logViewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        Text("IP: $ip")
        Text("Gateway: $gateway")
        Text("Network type: $networkType")
        NumberOutlinedTextField(
            label = "Local Port",
            isErrorOnOutlineTextFieldValueChange = { setNewLocalPort(it, logViewModel) },
            value = logViewModel.localPort.value.toString()
        )

    }
}

