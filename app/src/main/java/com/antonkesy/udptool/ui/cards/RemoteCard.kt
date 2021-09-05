package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.setNewRemoteIP
import com.antonkesy.udptool.util.setNewRemotePort

@Composable
fun RemoteCard(viewModel: MessageLogViewModel) {
    val label = "Remote"
    CardListCard(
        label = label,
        dialogText = "",
        cardHeader = { CardHeader(label = label) },
        content = {
            RemoteContent(
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun RemoteContent(
    viewModel: MessageLogViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        NumberOutlinedTextField(
            "IP",
            value = viewModel.remoteIP.value.toString(),
            isErrorOnOutlineTextFieldValueChange = { setNewRemoteIP(it, viewModel) })
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 12.dp)) {
            NumberOutlinedTextField(
                "Port",
                value = viewModel.remotePort.value.toString(),
                isErrorOnOutlineTextFieldValueChange = { setNewRemotePort(it, viewModel) })
        }
    }
}
