package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonkesy.udptool.ui.theme.UDPToolTheme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainView()
}

@ExperimentalAnimationApi
@Composable
fun MainView() {
    UDPToolTheme {
        Scaffold { innerPadding ->
            CardList(paddingValues = innerPadding)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CardList(paddingValues: PaddingValues) {
    Column(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Column(Modifier.weight(1.0f, false)) {
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
        }
        Column() {
            MessagesList()
        }
    }
}

@Composable
fun MessagesList() {
    val messages = mutableListOf("message 1", "message 2")
    LazyColumn() {
        items(items = messages) {
            Text(text = it)
        }
    }
}

@Composable
fun IPConfigCardContent(label: String, modifier: Modifier) {
    Column(modifier) {
        CardHeader(label)
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        Row {
            Text(text = "Local Port")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
    }
}


@Composable
fun RemoteContent(label: String, modifier: Modifier) {
    Column(modifier = modifier) {
        CardHeader(label)
        Row() {
            Text(text = "IP")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
        Row() {
            Text(text = "Port")
            TextField(value = "", onValueChange = {/*TODO*/ })
        }
    }
}

@Composable
fun MessagesCardContent(label: String, modifier: Modifier) {
    Column(modifier = modifier) {
        CardHeader(label)
        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
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

@Composable
fun CardHeader(label: String) {
    Text(label, fontWeight = FontWeight.Bold, fontSize = 17.sp)
}