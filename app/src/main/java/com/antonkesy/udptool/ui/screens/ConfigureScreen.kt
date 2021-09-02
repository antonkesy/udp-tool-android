package com.antonkesy.udptool.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.cards.DeviceCard
import com.antonkesy.udptool.ui.cards.MessageCard
import com.antonkesy.udptool.ui.cards.RemoteCard

@ExperimentalAnimationApi
@Composable
fun ConfigureScreen(paddingValues: PaddingValues) {
    Column(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(Modifier.wrapContentHeight()) {
            DeviceCard()
            RemoteCard()
            MessageCard()
        }

    }
}