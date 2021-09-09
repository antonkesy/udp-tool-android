package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.R
import com.antonkesy.udptool.ui.NumberOutlinedTextField
import com.antonkesy.udptool.ui.dialogs.ClearLogDialogBox
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.util.isStringLegalBufferSize

@Composable
fun LogInfoCard(logViewModel: MessageLogViewModel) {
    val label = "Logging"
    CardListCard(
        label = label,
        dialogText = "",
        cardHeader = { CardHeader(label = label) },
        content = {
            LogInfoCardContent(
                viewModel = logViewModel
            )
        }
    )
}

@Composable
fun LogInfoCardContent(
    viewModel: MessageLogViewModel
) {
    Column(Modifier.fillMaxWidth()) {
        SetBufferSize(viewModel)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Message decoding")
            SwitchLogModeDropDown(viewModel = viewModel)
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 12.dp)) {
            ClearLogButton(viewModel = viewModel)
        }

    }
}


@Composable
fun SetBufferSize(viewModel: MessageLogViewModel) {
    NumberOutlinedTextField(
        label = "buffer size (byte)",
        value = viewModel.bufferSize.value.toString(),
        isErrorOnOutlineTextFieldValueChange = {
            if (isStringLegalBufferSize(it)) {
                viewModel.setBufferSize(Integer.parseInt(it))
                return@NumberOutlinedTextField false
            } else {
                return@NumberOutlinedTextField true
            }
        })
}

@Composable
fun ClearLogButton(viewModel: MessageLogViewModel) {
    var isOpen by remember { mutableStateOf(false) }
    Button(onClick = { isOpen = true }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                contentDescription = ""
            )
            Text("Clear log")
        }
    }
    ClearLogDialogBox(
        isOpen = isOpen,
        onConfirm = {
            viewModel.setLogMessages(mutableListOf())
            isOpen = false
        },
        onDismissRequest = { isOpen = false })
}