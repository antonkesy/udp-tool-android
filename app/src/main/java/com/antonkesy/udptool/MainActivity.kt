package com.antonkesy.udptool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.ui.navigation.BottomNavigationWithOnlySelectedLabels
import com.antonkesy.udptool.ui.navigation.NavCategories
import com.antonkesy.udptool.ui.navigation.Navigation
import com.antonkesy.udptool.ui.theme.UDPToolTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val messageViewModel: MessageLogViewModel by viewModels()

        messageViewModel.generateTestLogMessages()

        setContent {
            MainView(messageViewModel, onSendAttachmentClick = { sendAttachment() }, { "" })
        }


    }

    private fun sendAttachment() {
        //TODO
    }
}

@ExperimentalAnimationApi
@Composable
fun MainView(
    logViewModel: MessageLogViewModel, onSendAttachmentClick: () -> Unit,
    onSendMessageClick: (message: String) -> Unit
) {
    var isSplashScreenShowing by remember { mutableStateOf(true) }
    UDPToolTheme {
        val navController = rememberNavController()

        LaunchedEffect(key1 = true) {
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
            AnimatedVisibility(
                !isSplashScreenShowing,
                enter = slideInVertically(initialOffsetY = { 300 })
            ) {
                BottomNavigationWithOnlySelectedLabels(
                    items = bottomNavigationItems,
                    navController = navController
                )
            }
        }) { innerPadding ->
            Navigation(
                navController = navController,
                innerPadding = innerPadding,
                logViewModel = logViewModel,
                onSendAttachmentClick = onSendAttachmentClick,
                onSendMessageClick = onSendMessageClick
            )
        }
    }
}
