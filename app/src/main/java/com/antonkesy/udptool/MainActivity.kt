package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
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
        }) { innerPadding ->
            Navigation(navController = navController, innerPadding = innerPadding)
        }
    }
}
