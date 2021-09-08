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
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.isTimeOutLegal

@Composable
fun MessageCard(logViewModel: MessageLogViewModel) {
    val label = "Send"
    CardListCard(
        label = label,
        dialogText = "",
        cardHeader = {
            val isMsg by logViewModel.isMessage.observeAsState(true)
            CardHeaderSwitch(label = label, isChecked = isMsg, onCheckedChange = {
                logViewModel.setIsMessage(it)
            })
        },
        content = {
            MessagesCardContent(
                logViewModel = logViewModel
            )
        }
    )
}


@Composable
fun MessagesCardContent(
    logViewModel: MessageLogViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val isMsg by logViewModel.isMessage.observeAsState(false)
        val isTimeOut by logViewModel.isTimeOutTime.observeAsState(false)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberOutlinedTextField(
                "Timeout",
                modifier = Modifier.weight(1.0f),
                isActive = isTimeOut && isMsg,
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
                checked = isTimeOut && isMsg,
                enabled = isMsg,
                onCheckedChange = { logViewModel.setIsTimeOutTime(it) })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Message coding")
            SwitchLogModeDropDown(isMsg, viewModel = logViewModel)
        }
    }
}

@Composable
fun SwitchLogModeDropDown(isEnabled: Boolean, viewModel: MessageLogViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val currentItem by viewModel.messageCoding.observeAsState(ASCII)
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
                viewModel.setMessageCoding(ASCII)
                expanded = false
            }) {
                Text(stringResource(id = ASCII.nameId))
            }
            DropdownMenuItem(onClick = {
                viewModel.setMessageCoding(HEX)
                expanded = false
            }) {
                Text(stringResource(id = HEX.nameId))
            }
        }
    }
}
