package com.antonkesy.udptool.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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


@ExperimentalAnimationApi
@Composable
fun CardList(paddingValues: PaddingValues) {
    Column(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(Modifier.fillMaxHeight()) {
            val modifierCardPadding = Modifier.padding(15.dp)
            CardListCard(
                label = "Device",
                content = { IPConfigCardContent(label = "Device", modifierCardPadding) })
            CardListCard(
                label = "Remote",
                content = { RemoteContent(label = "Remote", modifierCardPadding) })
            CardListCard(
                label = "Messages",
                content = { MessagesCardContent(label = "Messages", modifierCardPadding) })
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
fun CardListCard(label: String, content: @Composable () -> Unit) {
    var isExtended by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                isExtended = !isExtended
            },
        elevation = 10.dp
    ) {
        Column {
            AnimatedVisibility(visible = isExtended) { content() }
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
