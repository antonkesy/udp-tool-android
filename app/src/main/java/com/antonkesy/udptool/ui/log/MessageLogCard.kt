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
import com.antonkesy.udptool.messages.DeviceLogMessage
import com.antonkesy.udptool.messages.ILogMessage
import com.antonkesy.udptool.messages.MessageLog
import com.antonkesy.udptool.messages.SocketLogMessage
import com.antonkesy.udptool.ui.dialogs.DetailDialog
import com.antonkesy.udptool.util.getDataAsASCIIString


@Composable
fun MessagesLogList(logViewModel: MessageLogViewModel) {
    val messages: List<ILogMessage> by logViewModel.logMessages.observeAsState(emptyList())

    LazyColumn(Modifier.padding(5.dp)) {
        items(items = messages) {
            MessageLogItem(message = it, viewModel = logViewModel)
        }
    }
}


@Composable
fun MessageLogItem(message: ILogMessage, viewModel: MessageLogViewModel) {
    val showDetailDialog = remember { mutableStateOf(false) }
    DetailDialog(logMessage = message, showDetailDialog, viewModel = viewModel)
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
        when (message) {
            is DeviceLogMessage -> DeviceLogMessageContent(message = message)
            is SocketLogMessage -> SocketLogMessageContent(message = message)
            is MessageLog -> MessageLogMessageContent(message = message)
        }
    }
}

@Composable
fun SocketLogMessageContent(message: SocketLogMessage) {
    Text(
        text = message.info,
        textAlign = TextAlign.Start,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    )
}

@Composable
fun MessageLogMessageContent(message: MessageLog) {
    Text(
        text = getDataAsASCIIString(message.data),
        textAlign = TextAlign.Start,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    )
}


@Composable
fun DeviceLogMessageContent(message: DeviceLogMessage) {
    Text(
        text = message.info,
        textAlign = TextAlign.Start,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    )
}