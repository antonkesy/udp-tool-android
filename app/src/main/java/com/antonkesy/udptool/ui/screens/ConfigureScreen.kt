package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.cards.DeviceCard
import com.antonkesy.udptool.ui.cards.MessageCard
import com.antonkesy.udptool.ui.cards.RemoteCard
import com.antonkesy.udptool.ui.cards.ToggleLogCard
import com.antonkesy.udptool.ui.log.MessageLogViewModel

@Composable
fun ConfigureScreen(paddingValues: PaddingValues,logViewModel: MessageLogViewModel) {
    LazyColumn(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        item {
            DeviceCard()
            RemoteCard()
            MessageCard(logViewModel)
            ToggleLogCard()
        }
    }
}