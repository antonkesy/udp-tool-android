package com.antonkesy.udptool.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.messages.DeviceLogMessage
import com.antonkesy.udptool.messages.ILogMessage
import com.antonkesy.udptool.messages.MessageLog
import com.antonkesy.udptool.messages.SocketLogMessage
import com.antonkesy.udptool.ui.log.ASCII
import com.antonkesy.udptool.ui.log.HEX
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.getDataAsASCIIString
import com.antonkesy.udptool.util.getDataAsHexString

@Composable
fun DetailDialog(
    logMessage: ILogMessage,
    showDetailDialog: MutableState<Boolean>,
    viewModel: MessageLogViewModel
) {
    if (showDetailDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDetailDialog.value = false
            },
            title = {
                Text(logMessage.title)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDetailDialog.value = false
                    },
                ) {
                    Text("ok")
                }
            },
            text = {
                DetailDialogContent(logMessage, viewModel = viewModel)
            }
        )
    }
}

@Composable
fun DetailDialogContent(logMessage: ILogMessage, viewModel: MessageLogViewModel) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(text = "Time: ")
            Text(text = logMessage.time)
        }
        DetailContent(logMessage = logMessage, viewModel = viewModel)
    }
}

@Composable
fun DetailContent(logMessage: ILogMessage, viewModel: MessageLogViewModel) {
    when (logMessage) {
        is DeviceLogMessage -> DetailContentDeviceLog(logMessage = logMessage)
        is SocketLogMessage -> DetailContentSocketLog(logMessage = logMessage)
        is MessageLog -> DetailContentMessageLog(logMessage = logMessage, viewModel = viewModel)
    }
}

@Composable
fun DetailContentDeviceLog(logMessage: DeviceLogMessage) {
    Column(Modifier.fillMaxWidth()) {
        Text(logMessage.info)
    }
}

@Composable
fun DetailContentMessageLog(logMessage: MessageLog, viewModel: MessageLogViewModel) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Message:")
        val data = logMessage.data
        Text(
            when (viewModel.messageCoding.value) {
                ASCII -> {
                    getDataAsASCIIString(data)
                }
                HEX -> {
                    getDataAsHexString(data)
                }
                else -> ""
            }
        )
    }
}

@Composable
fun DetailContentSocketLog(logMessage: SocketLogMessage) {
    Column(Modifier.fillMaxWidth()) {
        Text(logMessage.info)
    }
}