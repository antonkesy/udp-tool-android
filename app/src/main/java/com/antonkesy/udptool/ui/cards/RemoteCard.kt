package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField

@ExperimentalAnimationApi
@Composable
fun RemoteCard() {
    CardListCard(
        label = "Remote",
        content = {
            RemoteContent(
                onRemoteIPChange = {/*TODO*/true },
                onRemotePortChange = {/*TODO*/true })

        }, dialogText = "text"
    )
}

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
