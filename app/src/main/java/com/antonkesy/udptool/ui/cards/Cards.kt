package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonkesy.udptool.ui.HelpDialogBoxButton


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
    label: String, dialogText: String, content: @Composable () -> Unit
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
                dialogText = dialogText,
                isExtended = isExtended,
                content = content,
                onToggleExtended = { isExtended = !isExtended }
            )
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun CardHeaderRow(
    label: String,
    isExtended: Boolean,
    onToggleExtended: () -> Unit,
    dialogText: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CardHeader(label)
            AnimatedVisibility(
                visible = isExtended,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                HelpDialogBoxButton(
                    dialogTitle = label,
                    dialogText = dialogText
                )
            }
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

@ExperimentalAnimationApi
@Composable
fun CardExtendedContent(
    title: String,
    dialogText: String,
    isExtended: Boolean,
    content: @Composable () -> Unit,
    onToggleExtended: () -> Unit
) {
    CardHeaderRow(
        label = title,
        dialogText = dialogText,
        isExtended = isExtended,
        onToggleExtended = { onToggleExtended() })
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
            content()
        }
    }
}
