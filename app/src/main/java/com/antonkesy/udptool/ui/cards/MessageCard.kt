package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.HelpDialogBoxButton
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@ExperimentalAnimationApi
@Composable
fun MessageCard() {
    val label = "Message"
    CardListCard(
        label = label,
        content = {
            MessagesCardContent(
                onTimeoutToggle = {/*TODO*/ },
                onTimeoutChange = {/*TODO*/ true },
                helpDialogBoxButton = {
                    HelpDialogBoxButton(
                        dialogTitle = label,
                        dialogText = "text"
                    )
                }
            )
        }
    )
}

@Composable
fun MessagesCardContent(
    onTimeoutToggle: (isEnabled: Boolean) -> Unit,
    onTimeoutChange: (text: String) -> Boolean, helpDialogBoxButton: @Composable () -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        helpDialogBoxButton()
        var isTimeoutEnabled by remember { mutableStateOf(true) }
        Row {
            Text("Timeout")
            Switch(
                checked = isTimeoutEnabled,
                onCheckedChange = { isTimeoutEnabled = it;onTimeoutToggle(it) })
            NumberOutlinedTextField("Timeout", onTimeoutChange)
        }
        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
}

