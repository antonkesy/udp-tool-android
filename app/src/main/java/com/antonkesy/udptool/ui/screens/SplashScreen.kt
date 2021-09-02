package com.antonkesy.udptool.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController, nextStartRoute: String) {
    Text("UDP Tool")
}