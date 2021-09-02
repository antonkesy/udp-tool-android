package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.cards.DeviceCard
import com.antonkesy.udptool.ui.cards.RemoteCard
import com.antonkesy.udptool.ui.cards.ToggleLogCard

@Composable
fun ConfigureScreen(paddingValues: PaddingValues) {
    Column(
        Modifier
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        DeviceCard()
        RemoteCard()
        ToggleLogCard()
    }
}