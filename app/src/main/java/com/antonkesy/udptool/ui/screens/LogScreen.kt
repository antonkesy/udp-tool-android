package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    var sendText by remember { mutableStateOf("") }
    Card(elevation = 3.dp) {
        Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
            TextField(value = sendText, onValueChange = { sendText = it }, Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ },
                Modifier.wrapContentWidth()
            ) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "send")
            }
        }
    }
}