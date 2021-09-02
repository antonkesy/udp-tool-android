package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.ui.log.MessagesLogList

@Composable
fun LogScreen(paddingValues: PaddingValues, logViewModel: MessageLogViewModel) {
    Scaffold(
        Modifier.padding(paddingValues),
        bottomBar = { SendMessageBottomBar() }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MessagesLogList(logViewModel)
        }
    }
}

@Composable
fun SendMessageBottomBar() {
    Row {
        TextField(value = "", onValueChange = {/*TODO*/ })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "send")
        }
    }
}