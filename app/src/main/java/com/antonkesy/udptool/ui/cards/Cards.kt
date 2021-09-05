package com.antonkesy.udptool.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonkesy.udptool.ui.dialogs.HelpDialogBoxButton

@Composable
fun CardHeader(label: String) {
    Text(label, fontWeight = FontWeight.Bold, fontSize = 17.sp)
}

@Composable
fun CardHeaderSwitch(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            label,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier.padding(end = 12.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun CardListCard(
    label: String,
    dialogText: String,
    cardHeader: @Composable (label: String) -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 6.dp),
        elevation = 10.dp
    ) {
        Column {
            CardContent(
                title = label,
                dialogText = dialogText,
                content = content,
                cardHeader = cardHeader
            )
        }
    }
}


@Composable
fun CardHeaderRow(
    label: String,
    dialogText: String, cardHeader: @Composable (label: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        cardHeader(label)
        HelpDialogBoxButton(
            dialogTitle = label,
            dialogText = dialogText
        )
    }

}

@Composable
fun CardContent(
    title: String,
    dialogText: String,
    cardHeader: @Composable (label: String) -> Unit,
    content: @Composable () -> Unit
) {
    CardHeaderRow(
        label = title,
        dialogText = dialogText,
        cardHeader = cardHeader
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        content()
    }
}
