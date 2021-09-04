package com.antonkesy.udptool.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.MessageSendTextFieldRow
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.ui.log.MessagesLogList

@Composable
fun LogScreen(
    paddingValues: PaddingValues,
    logViewModel: MessageLogViewModel,
    onSendAttachmentClick: () -> Unit,
    onSendMessageClick: (message: String) -> Unit
) {
    val canSendMessages: Boolean by logViewModel.canSendMessages.observeAsState(true)
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            if (canSendMessages) SendMessageBottomBar(
                onSendAttachmentClick,
                onSendMessageClick
            )
        }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            MessagesLogList(logViewModel)
        }
    }
}

@Composable
fun SendMessageBottomBar(
    onSendAttachmentClick: () -> Unit,
    onSendMessageClick: (message: String) -> Unit
) {
    Card(
        elevation = 3.dp, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        MessageSendTextFieldRow(onSendAttachmentClick, onSendMessageClick)
    }
}