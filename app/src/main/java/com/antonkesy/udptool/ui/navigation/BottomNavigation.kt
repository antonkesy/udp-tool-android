package com.antonkesy.udptool.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.antonkesy.udptool.ui.navigation.NavCategories
import com.antonkesy.udptool.ui.screens.ConfigureScreen
import com.antonkesy.udptool.ui.screens.LogScreen


@Composable
fun BottomNavigationWithOnlySelectedLabels(
    items: List<NavCategories>, navController: NavController
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { _, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(item.drawableId), contentDescription = null) },
                label = {
                    Text(stringResource(id = item.resourceId))
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController, startDestination = NavCategories.Configure.route) {
        composable(NavCategories.Configure.route) {
            ConfigureScreen(innerPadding)
        }
        composable(NavCategories.Log.route) {
            LogScreen(innerPadding)
        }

    }
}