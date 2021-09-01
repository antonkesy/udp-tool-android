package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.antonkesy.udptool.ui.cards.CardList
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
        Scaffold(floatingActionButton = { StartStopFloatingActionButton() }) { innerPadding ->
            CardList(paddingValues = innerPadding)
        }
    }
}

@Composable
fun StartStopFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
            contentDescription = "start listening"
        )
    }
}
