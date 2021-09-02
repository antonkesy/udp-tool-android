package com.antonkesy.udptool.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, nextStartRoute: String) {
    Text("UDP Tool")
    produceState(initialValue = -1) {
        delay(1500)
        navController.popBackStack()
        navController.navigate(nextStartRoute)
    }
}