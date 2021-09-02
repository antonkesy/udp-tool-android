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
import androidx.navigation.compose.rememberNavController
import com.antonkesy.udptool.ui.BottomNavigationWithOnlySelectedLabels
import com.antonkesy.udptool.ui.Navigation
import com.antonkesy.udptool.ui.navigation.NavCategories
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
        val navController = rememberNavController()

        val bottomNavigationItems = listOf(
            NavCategories.Configure,
            NavCategories.Log
        )

        Scaffold(bottomBar = {
            BottomNavigationWithOnlySelectedLabels(
                items = bottomNavigationItems,
                navController = navController
            )
        }, floatingActionButton = { StartStopFloatingActionButton() }) { innerPadding ->
            Navigation(navController = navController, innerPadding = innerPadding)
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
