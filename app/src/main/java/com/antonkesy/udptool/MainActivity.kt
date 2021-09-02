package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.antonkesy.udptool.ui.navigation.BottomNavigationWithOnlySelectedLabels
import com.antonkesy.udptool.ui.navigation.NavCategories
import com.antonkesy.udptool.ui.navigation.Navigation
import com.antonkesy.udptool.ui.theme.UDPToolTheme
import kotlinx.coroutines.delay

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
    var isSplashScreenShowing by remember { mutableStateOf(true) }
    UDPToolTheme {
        val navController = rememberNavController()

        produceState(initialValue = -1) {
            delay(750)
            isSplashScreenShowing = false
            navController.popBackStack()
            navController.navigate(NavCategories.Configure.route)
        }

        val bottomNavigationItems = listOf(
            NavCategories.Configure,
            NavCategories.Log
        )

        Scaffold(bottomBar = {
            if (!isSplashScreenShowing) {
                BottomNavigationWithOnlySelectedLabels(
                    items = bottomNavigationItems,
                    navController = navController
                )
            }
        }) { innerPadding ->
            Navigation(navController = navController, innerPadding = innerPadding)
        }
    }
}
