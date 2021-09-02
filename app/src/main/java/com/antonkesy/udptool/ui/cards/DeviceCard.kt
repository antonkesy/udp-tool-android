package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@ExperimentalAnimationApi
@Composable
fun DeviceCard() {
    CardListCard(
        label = "Device",
        content = {
            IPConfigCardContent {/*TODO*/ true }
        }, dialogText = "text"
    )
}

@Composable
fun IPConfigCardContent(
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

