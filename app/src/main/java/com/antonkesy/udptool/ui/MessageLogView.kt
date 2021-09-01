package com.antonkesy.udptool.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.antonkesy.udptool.data.DeviceLogMessage
import com.antonkesy.udptool.data.ILogMessage


@Composable
fun MessagesLogList() {
    val messages = mutableListOf<ILogMessage>(
        DeviceLogMessage("message 1"),
        DeviceLogMessage("message 2"),
        DeviceLogMessage("message 3")
    )
    LazyColumn() {
        items(items = messages) {
            MessageLogItem(it)
        }
    }
}


@Composable
fun MessageLogItem(message: ILogMessage) {

}

