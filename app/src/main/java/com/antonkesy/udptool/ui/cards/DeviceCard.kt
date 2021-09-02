package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@Composable
fun DeviceCard() {
    val label = "Device"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            DeviceCardContent(
                onLocalPortValueChanged = {/*TODO*/ true }
            )
        }
    )
}

@Composable
fun DeviceCardContent(
    onLocalPortValueChanged: (text: String) -> Boolean
) {
    Column(Modifier.fillMaxWidth()) {
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        NumberOutlinedTextField(
            label = "Local Port",
            onOutlineTextFieldValueChange = onLocalPortValueChanged
        )

    }
}

