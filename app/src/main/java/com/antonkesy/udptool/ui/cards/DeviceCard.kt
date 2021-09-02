package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.HelpDialogBoxButton
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@ExperimentalAnimationApi
@Composable
fun DeviceCard() {
    val label = "Device"
    CardListCard(
        label = label,
        content = {
            DeviceCardContent(
                onLocalPortValueChanged = {/*TODO*/ true },
                helpDialogBoxButton = {
                    HelpDialogBoxButton(
                        dialogTitle = label,
                        dialogText = "Text"
                    )
                }
            )
        }
    )
}

@Composable
fun DeviceCardContent(
    onLocalPortValueChanged: (text: String) -> Boolean, helpDialogBoxButton: @Composable () -> Unit
) {

    Column(Modifier.fillMaxWidth()) {
        helpDialogBoxButton()
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        NumberOutlinedTextField(
            label = "Local Port",
            onOutlineTextFieldValueChange = onLocalPortValueChanged
        )

    }
}

