package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.HelpDialogBoxButton
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@ExperimentalAnimationApi
@Composable
fun RemoteCard() {
    val label = "Remote"
    CardListCard(
        label = label,
        content = {
            RemoteContent(
                onRemoteIPChange = {/*TODO*/true },
                onRemotePortChange = {/*TODO*/true },
                helpDialogBoxButton = {
                    HelpDialogBoxButton(
                        dialogTitle = label,
                        dialogText = "text"
                    )
                })

        }
    )
}

@Composable
fun RemoteContent(
    onRemoteIPChange: (ip: String) -> Boolean,
    onRemotePortChange: (port: String) -> Boolean, helpDialogBoxButton: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        helpDialogBoxButton()
        NumberOutlinedTextField("IP", onRemoteIPChange)
        NumberOutlinedTextField("Port", onRemotePortChange)
    }
}
