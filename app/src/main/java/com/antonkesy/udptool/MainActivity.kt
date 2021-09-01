package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antonkesy.udptool.ui.theme.UDPToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainView()
}

@Composable
fun MainView() {
    UDPToolTheme {
        Scaffold { innerPadding ->
            CardList(paddingValues = innerPadding)
        }
    }
}

@Composable
fun CardList(paddingValues: PaddingValues) {
    LazyColumn(
        Modifier
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        item {
            val modifierCardPadding = Modifier.padding(15.dp)
            CardListCard(content = { ModeContent(modifierCardPadding) })
            CardListCard(content = { IPConfigCardContent(modifierCardPadding) })
            CardListCard(content = { RemoteContent(modifierCardPadding) })
            CardListCard(content = { MessagesCardContent(modifierCardPadding) })
        }
    }
}

@Composable
fun ModeContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Mode")
        Switch(checked = false, onCheckedChange = {/*TODO()*/ })
    }
}

@Composable
fun IPConfigCardContent(modifier: Modifier) {
    Column(modifier) {
        Text("Your device")
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
        Text(text = "Local Port")
        TextField(value = "", onValueChange = {/*TODO*/ })
    }
}


@Composable
fun RemoteContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Remote")
        Text(text = "IP")
        TextField(value = "", onValueChange = {/*TODO*/ })
        Text(text = "Port")
        TextField(value = "", onValueChange = {/*TODO*/ })
    }
}

@Composable
fun MessagesCardContent(modifier: Modifier) {
    val messages = mutableListOf("message 1", "message 2")

    Column(modifier = modifier) {
        Text(text = "Messages")
        LazyRow {
            items(items = messages) {
                Text(text = it)
            }
        }

        Row {
            TextField(value = "", onValueChange = {/*TODO*/ })
            Button(onClick = { /*TODO*/ }) {
                Text(text = "send")
            }
        }
    }
}

@Composable
fun CardListCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 10.dp
    ) {
        content()
    }
}
