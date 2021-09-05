package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.isTimeOutLegal

@Composable
fun ReceiveCard(logViewModel: MessageLogViewModel) {
    val label = "Listen"
    CardListCard(
        label = label,
        dialogText = "",
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
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Listen for messages")
            val isMsg by logViewModel.isListening.observeAsState(true)
            Switch(
                checked = isMsg,
                onCheckedChange = {
                    logViewModel.setIsListening(it)
                }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberOutlinedTextField(
                "Listen interval",
                modifier = Modifier.weight(1.0f),
                isActive = logViewModel.isListening.value == true && logViewModel.isListeningInterval.value == true,
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
            val isListening by logViewModel.isListeningInterval.observeAsState(true)
            Switch(
                checked = isListening && logViewModel.isListening.value == true,
                onCheckedChange = { logViewModel.setIsListeningInterval(it) })
        }
    }
}
