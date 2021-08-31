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
import com.antonkesy.udptool.ui.screens.DetailScreen
import com.antonkesy.udptool.ui.theme.UDPToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
    //TODO
    //layout should be like keep
    //add connection listening and save history
    //history as bottom sheet to pull up and full screen
    //top has info of settings
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainView()
}

@Composable
fun MainView() {
    UDPToolTheme {
        Scaffold(floatingActionButton = { AddUDPItemFloatBtn() }) { innerPadding ->
            //CardList(paddingValues = innerPadding)
            DetailScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun AddUDPItemFloatBtn() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Text(text = "Add")
    }
}

@Composable
fun CardList(paddingValues: PaddingValues) {
    LazyColumn(
        Modifier
            .padding(paddingValues = paddingValues)
    ) {
        item {
            val modifierCardPadding = Modifier.padding(15.dp)
            CardListCard(content = { IPConfigCardContent(modifierCardPadding) })
            CardListCard(content = { ModeContent(modifierCardPadding) })
            CardListCard(content = { LocalPortContent(modifierCardPadding) })
            CardListCard(content = { RemoteContent(modifierCardPadding) })
            CardListCard(content = { MessagesCardContent(modifierCardPadding) })
        }
    }
}

@Composable
fun IPConfigCardContent(modifier: Modifier) {
    Column(modifier) {
        Text("IP: 0.0.0.0")
        Text("Gateway: 0.0.0.0")
        Text("Network type: LAN")
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
fun LocalPortContent(modifier: Modifier) {
    Column(modifier = modifier) {
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
            .padding(15.dp),
        elevation = 10.dp
    ) {
        content()
    }
}