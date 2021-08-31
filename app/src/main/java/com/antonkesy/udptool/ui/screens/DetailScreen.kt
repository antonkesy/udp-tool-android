package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.CardListCard

@Composable
fun DetailScreen(modifier: Modifier) {
    val messages = mutableListOf("message 1", "message 2", "message 3")

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { BottomBarSendMessage() }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
            CurrentSettings()
            LazyColumn(modifier = modifier) {
                items(messages) {
                    Text(text = it)
                }
            }
        }
    }

}

@Composable
fun BottomBarSendMessage() {
    Row(Modifier.fillMaxWidth()) {
        Text(text = "send message")
        Button(onClick = {/*TODO*/ }) {
        }
    }
}

@Composable
fun CurrentSettings() {
    CardListCard {
        Text(text = "IP =")
    }
}
