package com.antonkesy.udptool.ui.log

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.messages.ILogMessage
import com.antonkesy.udptool.ui.dialogs.DetailDialog


@Composable
fun MessagesLogList(logViewModel: MessageLogViewModel) {
    val messages: List<ILogMessage> by logViewModel.logMessages.observeAsState(emptyList())

    LazyColumn {
        items(items = messages) {
            MessageLogItem(it)
        }
    }
}


@Composable
fun MessageLogItem(message: ILogMessage) {
    val showDetailDialog = remember { mutableStateOf(false) }
    DetailDialog(logMessage = message,showDetailDialog)
    Row(
        Modifier
            .clickable {
                showDetailDialog.value = true
            }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "[${message.time}]:",
            textAlign = TextAlign.Start,
            maxLines = 1
        )
        Text(
            text = message.title,
            textAlign = TextAlign.Start,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
    }
}