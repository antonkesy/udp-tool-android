package com.antonkesy.udptool.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.data.DeviceLogMessage
import com.antonkesy.udptool.data.ILogMessage
import java.text.SimpleDateFormat


@Composable
fun MessagesLogList() {
    val messages = mutableListOf<ILogMessage>()
    for (i in 1..100) {
        messages.add(0, DeviceLogMessage("message $i"))
    }

    LazyColumn {
        items(items = messages) {
            MessageLogItem(it)
        }
    }
}


@Composable
fun MessageLogItem(message: ILogMessage) {
    Row(
        Modifier
            .clickable { /*TODO(open detail view)*/ }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val formatter = SimpleDateFormat("HH:mm:ss.SSS")
        Text(text = formatter.format(message.time) + ":", textAlign = TextAlign.Start, maxLines = 1)
        Text(
            text = message.message,
            textAlign = TextAlign.Start,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
    }
}