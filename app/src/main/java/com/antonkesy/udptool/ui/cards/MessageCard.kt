package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.log.ASCII
import com.antonkesy.udptool.ui.log.HEX
import com.antonkesy.udptool.ui.log.LogMessageCoding

@Composable
fun MessageCard() {
    val label = "Message"
    CardListCard(
        label = label,
        dialogText = "",
        content = {
            MessagesCardContent(
                onTimeoutToggle = {/*TODO*/ },
                onTimeoutChange = {/*TODO*/ true })
        }
    )
}

@Composable
fun MessagesCardContent(
    onTimeoutToggle: (isEnabled: Boolean) -> Unit,
    onTimeoutChange: (text: String) -> Boolean
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            var isTimeoutEnabled by remember { mutableStateOf(true) }
            NumberOutlinedTextField(
                "Timeout",
                onTimeoutChange,
                modifier = Modifier.weight(1.0f),
                isTimeoutEnabled
            )
            Switch(
                checked = isTimeoutEnabled,
                onCheckedChange = { isTimeoutEnabled = it;onTimeoutToggle(it) })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Message coding")
            SwitchLogModeDropDown()
        }
    }
}

@Composable
fun SwitchLogModeDropDown() {
    var expanded by remember { mutableStateOf(false) }
    //todo load from model
    var currentItem by remember { mutableStateOf<LogMessageCoding>(ASCII) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        DropdownMenuItem(onClick = { expanded = true }) {
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
