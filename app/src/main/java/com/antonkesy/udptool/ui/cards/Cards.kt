package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Help
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun CardList(paddingValues: PaddingValues) {
    //TODO(convert to lazyColumn)
    Column(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(Modifier.wrapContentHeight()) {
            DeviceCard()
            RemoteCard()
            MessageCard()
        }
        Column(
            Modifier
                .padding(start = 5.dp, end = 5.dp)
                .fillMaxHeight()
        ) {
            MessagesLogList()
        }
    }
}

@Composable
fun CardHeader(label: String) {
    Text(label, fontWeight = FontWeight.Bold, fontSize = 17.sp)
}

@ExperimentalAnimationApi
@Composable
fun CardListCard(
    label: String, content: @Composable () -> Unit, dialogText: String
) {
    var isExtended by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
            .clickable {
                isExtended = !isExtended
            },
        elevation = 10.dp
    ) {
        Column {
            CardExtendedContent(
                title = label,
                isExtended = isExtended,
                content = content, dialogText = dialogText
            )
            CardBottomRow(
                isExtended = isExtended, onToggleExtended = { isExtended = !isExtended },
                label = label
            )
        }
    }
}


@Composable
fun CardHeaderRow(label: String, dialogText: String) {
    var isDialogShown by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CardHeader(label)
        IconButton(
            onClick = { isDialogShown = true },
        ) {
            Icon(
                Icons.Outlined.Help,
                contentDescription = "help",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
    if (isDialogShown) {
        HelpDialogBox(
            isDialogShown,
            dialogTitle = label,
            dialogText = dialogText,
            onConfirmClick = { isDialogShown = false })
    }
}

@ExperimentalAnimationApi
@Composable
fun CardExtendedContent(
    title: String,
    dialogText: String,
    isExtended: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isExtended,
        enter = slideInVertically() + expandVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            CardHeaderRow(label = title, dialogText = dialogText)
            content()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CardBottomRow(
    isExtended: Boolean,
    onToggleExtended: () -> Unit,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        ) {
            AnimatedVisibility(
                visible = !isExtended, enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) { CardHeader(label) }
        }
        IconButton(
            onClick = { onToggleExtended() },
        ) {
            Icon(
                if (isExtended)
                    Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
}

@Composable
fun HelpDialogBox(
    isDialogShown: Boolean,
    dialogTitle: String,
    dialogText: String, onConfirmClick: () -> Unit
) {
    if (isDialogShown) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(dialogTitle)
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmClick()
                    },
                ) {
                    Text("Ok :)")
                }
            },
            text = {
                Text(dialogText)
            },
        )
    }
}
