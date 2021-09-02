package com.antonkesy.udptool.ui.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
            CardListCard(
                label = "Device",
                content = {
                    IPConfigCardContent {/*TODO*/ true }
                }, onHelpClick = {})
            CardListCard(
                label = "Remote",
                content = {
                    RemoteContent(
                        onRemoteIPChange = {/*TODO*/true },
                        onRemotePortChange = {/*TODO*/true })
                }, onHelpClick = {})
            CardListCard(
                label = "Messages",
                content = {
                    MessagesCardContent(
                        onTimeoutToggle = {/*TODO*/ },
                        onTimeoutChange = {/*TODO*/ true }
                    )
                }, onHelpClick = {})
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
fun CardListCard(label: String, content: @Composable () -> Unit, onHelpClick: () -> Unit) {
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
            AnimatedVisibility(visible = isExtended) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    CardHeaderRow(label = label, onHelpClick = onHelpClick)
                    content()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 15.dp)
                ) {
                    AnimatedVisibility(visible = !isExtended) { CardHeader(label) }
                }
                IconButton(
                    onClick = { isExtended = !isExtended },
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
    }
}

@Composable
fun CardHeaderRow(label: String, onHelpClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CardHeader(label)
        IconButton(
            onClick = { onHelpClick() },
        ) {
            Icon(
                Icons.Outlined.Help,
                contentDescription = "help",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
}