package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.udp.setNewRemoteIP
import com.antonkesy.udptool.udp.setNewRemotePort
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel

@Composable
fun RemoteCard(viewModel: MessageLogViewModel) {
    val label = "Remote"
    CardListCard(
        label = label,
        dialogText = "",
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
        NumberOutlinedTextField(
            "Port",
            value = viewModel.remotePort.value.toString(),
            isErrorOnOutlineTextFieldValueChange = { setNewRemotePort(it, viewModel) })
    }
}
