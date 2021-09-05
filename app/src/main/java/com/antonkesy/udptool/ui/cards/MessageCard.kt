package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.ASCII
import com.antonkesy.udptool.ui.log.HEX
import com.antonkesy.udptool.ui.log.LogMessageCoding
import com.antonkesy.udptool.ui.log.MessageLogViewModel

@Composable
fun MessageCard(logViewModel: MessageLogViewModel) {
    val label = "Message"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            MessagesCardContent(
                onTimeoutToggle = {/*TODO*/ },
                logViewModel = logViewModel
            )
        }
    )
}

@Composable
fun MessagesCardContent(
    onTimeoutToggle: (isEnabled: Boolean) -> Unit,
    logViewModel: MessageLogViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val canSendMessages: Boolean by logViewModel.canSendMessages.observeAsState(true)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Send messages")
            Switch(
                checked = canSendMessages,
                onCheckedChange = { logViewModel.setCanSendMessages(!canSendMessages) }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var isTimeoutEnabled by remember { mutableStateOf(true) }
            NumberOutlinedTextField(
                "Timeout",
                modifier = Modifier.weight(1.0f),
                isActive = isTimeoutEnabled,
                value = logViewModel.timeOutTime.value.toString(),
                isErrorOnOutlineTextFieldValueChange = {
                    if (isTimeOutLegal(it)) {
                        logViewModel.setTimeOutTime(Integer.parseInt(it))
                        return@NumberOutlinedTextField false
                    } else {
                        return@NumberOutlinedTextField true
                    }
                }
            )
            Switch(
                checked = isTimeoutEnabled && canSendMessages,
                onCheckedChange = { isTimeoutEnabled = it;onTimeoutToggle(it) })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Message coding")
            SwitchLogModeDropDown(canSendMessages)
        }
    }
}

@Composable
fun SwitchLogModeDropDown(isEnabled: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    //todo load from model
    var currentItem by remember { mutableStateOf<LogMessageCoding>(ASCII) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        DropdownMenuItem(onClick = { if (isEnabled) expanded = true }, enabled = isEnabled) {
            Text(stringResource(id = currentItem.nameId))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                currentItem = ASCII
                expanded = false
            }) {
                Text(stringResource(id = ASCII.nameId))
            }
            DropdownMenuItem(onClick = {
                currentItem = HEX
                expanded = false
            }) {
                Text(stringResource(id = HEX.nameId))
            }
        }
    }
}


fun isTimeOutLegal(value: String): Boolean {
    try {
        if (Integer.parseInt(value) in 0..Int.MAX_VALUE) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false
}