package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonkesy.udptool.ui.HelpDialogBoxButton

@Composable
fun CardHeader(label: String) {
    Text(label, fontWeight = FontWeight.Bold, fontSize = 17.sp)
}

@Composable
fun CardListCard(
    label: String, dialogText: String, content: @Composable () -> Unit
) {
    var isExtended by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .padding(15.dp)
            .clickable {
                isExtended = !isExtended
            },
        elevation = 10.dp
    ) {
        Column {
            CardContent(
                title = label,
                dialogText = dialogText,
                content = content
            )
        }
    }
}


@Composable
fun CardHeaderRow(
    label: String,
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
            HelpDialogBoxButton(
                dialogTitle = label,
                dialogText = dialogText
            )
        }
    }

}

@Composable
fun CardContent(
    title: String,
    dialogText: String,
    content: @Composable () -> Unit
) {
    CardHeaderRow(
        label = title,
        dialogText = dialogText
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        content()
    }
}
