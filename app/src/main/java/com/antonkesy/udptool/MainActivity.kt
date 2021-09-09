package com.antonkesy.udptool

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.antonkesy.udptool.data.store.loadSavedData
import com.antonkesy.udptool.data.store.observeToSaveData
import com.antonkesy.udptool.messages.DeviceLogMessage
import com.antonkesy.udptool.messages.MessageLog
import com.antonkesy.udptool.messages.SocketLogMessage
import com.antonkesy.udptool.messages.SocketLogMessageType
import com.antonkesy.udptool.udp.ISocketResponses
import com.antonkesy.udptool.udp.TimeOutReason
import com.antonkesy.udptool.udp.UDPSendReceive
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.ui.navigation.BottomNavigationWithOnlySelectedLabels
import com.antonkesy.udptool.ui.navigation.NavCategories
import com.antonkesy.udptool.ui.navigation.Navigation
import com.antonkesy.udptool.ui.theme.UDPToolTheme
import com.antonkesy.udptool.util.addSocketHotUpdate
import com.antonkesy.udptool.util.getLocalIpAddress
import kotlinx.coroutines.delay
import java.net.InetAddress


class MainActivity : ComponentActivity(), ISocketResponses {

    private lateinit var socket: UDPSendReceive
    private lateinit var socketThread: Thread
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userdata")
    private lateinit var viewModel: MessageLogViewModel

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val messageViewModel: MessageLogViewModel by viewModels()
        viewModel = messageViewModel

        setContent {
            MainView(
                messageViewModel,
                onSendMessageClick = { sendMessage(it) })
        }

        loadSavedData(viewModel = messageViewModel, dataStore = dataStore)

        observeToSaveData(
            viewModel = messageViewModel,
            dataStore = dataStore,
            lifecycleOwner = this
        )

        addSocketHotUpdate(
            logViewModel = messageViewModel,
            lifecycleOwner = this,
            { createSocketThread(logViewModel = messageViewModel) }
        )

        setLocalData(messageViewModel)

        createStartSocket(messageViewModel)
    }

    private fun createStartSocket(logViewModel: MessageLogViewModel) {
        //create socket when splash screen is over
        logViewModel.setLogMessages(emptyList())
        logViewModel.enableSocketCreation.observe(this,
            { createSocketThread(logViewModel) })
    }

    private fun createSocketThread(logViewModel: MessageLogViewModel) {
        if (::socketThread.isInitialized) {
            socket.kill()
        }
        if (logViewModel.enableSocketCreation.value == false) {
            return
        }
        try {
            socket = UDPSendReceive(
                logViewModel.localPort.value!!,
                logViewModel.remotePort.value!!,
                InetAddress.getByName(logViewModel.remoteIP.value),
                logViewModel.isTimeOutTime.value!!,
                logViewModel.timeOutTime.value!!,
                logViewModel.bufferSize.value!!,
                logViewModel.listenInterval.value!!,
                logViewModel.isListening.value!!,
                logViewModel.isListeningInterval.value!!,
                this
            )
            socketThread = Thread(socket)
            socketThread.start()
            setLocalData(logViewModel)

        } catch (e: Exception) {
            //TODO show user error
            Log.e("thread", "null pointer")
        }
    }

    override fun socketTimeOut(reason: TimeOutReason) {
        viewModel.addLogMessage(
            SocketLogMessage(
                SocketLogMessageType.TIMEOUT, when (reason) {
                    TimeOutReason.RECEIVE_TIMEOUT -> "receive timeout " + viewModel.listenInterval.value.toString() + "ms"
                    TimeOutReason.SEND_RESPONSE_TIMEOUT -> "send response timeout " + viewModel.timeOutTime.value.toString() + "ms"
                }
            )
        )
    }

    override fun ioException(stackTraceMessage: String) {
        viewModel.addLogMessage(
            SocketLogMessage(
                SocketLogMessageType.IOEXCEPTION,
                stackTraceMessage
            )
        )
    }

    override fun socketException(stackTraceMessage: String) {
        viewModel.addLogMessage(SocketLogMessage(SocketLogMessageType.EXCEPTION, stackTraceMessage))
    }

    override fun dataReceived(data: ByteArray) {
        viewModel.addLogMessage(MessageLog(isSend = false, data = data))
    }

    override fun socketStart() {
        viewModel.addLogMessage(SocketLogMessage(SocketLogMessageType.START, ""))
    }

    override fun socketClosed() {
        viewModel.addLogMessage(SocketLogMessage(SocketLogMessageType.CLOSED, ""))
    }

    override fun sendPacket(data: ByteArray) {
        viewModel.addLogMessage(MessageLog(isSend = true, data = data))
    }

    private fun sendMessage(message: String) {
        if (::socket.isInitialized) {
            val messageByteArray = message.toByteArray()
            socket.addMessageToQue(messageByteArray)
            viewModel.addLogMessage(DeviceLogMessage("add to send queue", message))
        }
    }

    private fun setLocalData(viewModel: MessageLogViewModel) {
        val ip = getLocalIpAddress()
        if (ip != null) {
            viewModel.setLocalIP(ip)
        } else {
            viewModel.setLocalIP("0.0.0.0")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainView(
    logViewModel: MessageLogViewModel,
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
            logViewModel.setSocketCreation(true)
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
                onSendMessageClick = onSendMessageClick
            )
        }
    }
}
