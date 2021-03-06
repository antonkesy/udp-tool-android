package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.antonkesy.udptool.R
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.isTimeOutLegal

@Composable
fun ReceiveCard(logViewModel: MessageLogViewModel) {
    val label = "Listen"
    CardListCard(
        label = label,
        dialogText = stringResource(id = R.string.receive_card_help),
        cardHeader = {
            val isListening by logViewModel.isListening.observeAsState(true)
            CardHeaderSwitch(label = label, isChecked = isListening, onCheckedChange = {
                logViewModel.setIsListening(it)
            })
        },
        content = {
            ReceiveCardContent(
                logViewModel = logViewModel
            )
        }
    )
}

@Composable
fun ReceiveCardContent(
    logViewModel: MessageLogViewModel
) {
    val isListening by logViewModel.isListening.observeAsState(false)
    val isListenInterval by logViewModel.isListeningInterval.observeAsState(false)
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberOutlinedTextField(
                "Listen interval",
                modifier = Modifier.weight(1.0f),
                isActive = isListening && isListenInterval,
                value = logViewModel.listenInterval.value.toString(),
                isErrorOnOutlineTextFieldValueChange = {
                    if (isTimeOutLegal(it)) {
                        logViewModel.setListenInterval(Integer.parseInt(it))
                        return@NumberOutlinedTextField false
                    } else {
                        return@NumberOutlinedTextField true
                    }
                }
            )
            Switch(
                checked = isListening && isListenInterval,
                enabled = isListening,
                onCheckedChange = { logViewModel.setIsListeningInterval(it) })
        }
    }
}
