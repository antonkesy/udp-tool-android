package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ToggleLogCard() {
    val label = "Logging"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            var isLogging by remember { mutableStateOf(false) }
            ToggleLogCardContent(
                isLogging = isLogging,
                onLoggingToggleClick = { isLogging = !isLogging }
            )
        }
    )
}

@Composable
fun ToggleLogCardContent(
    isLogging: Boolean,
    onLoggingToggleClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Button(onClick = { onLoggingToggleClick() }) {
            if (isLogging) {
                Text(text = "stop logging")
            } else {
                Text(text = "start logging")
            }

        }

    }
}

