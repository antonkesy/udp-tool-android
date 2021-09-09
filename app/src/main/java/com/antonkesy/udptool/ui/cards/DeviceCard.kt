package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.FakeOutlinedTextField
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.setNewLocalPort
import com.antonkesy.udptool.R

@Composable
fun DeviceCard(logViewModel: MessageLogViewModel) {
    val label = "Device"
    CardListCard(
        label = label,
        dialogText = stringResource(id = R.string.device_card_help),
        cardHeader = { CardHeader(label = label) },
        content = {
            DeviceCardContent(logViewModel)
        }
    )
}

@Composable
fun DeviceCardContent(
    logViewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        val ip by logViewModel.localIP.observeAsState("0.0.0.0")
        FakeOutlinedTextField(
            label = "IP",
            value = ip
        )
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 12.dp)) {
            NumberOutlinedTextField(
                label = "Local Port",
                isErrorOnOutlineTextFieldValueChange = { setNewLocalPort(it, logViewModel) },
                value = logViewModel.localPort.value.toString()
            )
        }
    }
}

