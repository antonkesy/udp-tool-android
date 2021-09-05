package com.antonkesy.udptool

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.antonkesy.udptool.messages.SocketLogMessage
import com.antonkesy.udptool.udp.ISocketResponses
import com.antonkesy.udptool.udp.UDPSendReceive
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import com.antonkesy.udptool.ui.navigation.BottomNavigationWithOnlySelectedLabels
import com.antonkesy.udptool.ui.navigation.NavCategories
import com.antonkesy.udptool.ui.navigation.Navigation
import com.antonkesy.udptool.ui.theme.UDPToolTheme
import com.antonkesy.udptool.util.addSocketHotUpdate
import kotlinx.coroutines.delay
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*


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
                onSendAttachmentClick = { sendAttachment() },
                { sendMessage(it) })
        }

        loadSavedData(viewModel = messageViewModel, dataStore = dataStore)

        observeToSaveData(
            viewModel = messageViewModel,
            dataStore = dataStore,
            lifecycleOwner = this
        )

        createSocketThread(logViewModel = messageViewModel)
        addSocketHotUpdate(
            logViewModel = messageViewModel,
            lifecycleOwner = this,
            { createSocketThread(logViewModel = messageViewModel) }
        )

        setLocalData(messageViewModel)

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                //TODO
            }
        }

    private fun sendAttachment() {
        startForResult.launch(Intent(Intent.ACTION_GET_CONTENT).setType("*/*"))
    }

    private fun createSocketThread(logViewModel: MessageLogViewModel) {
        if (::socketThread.isInitialized) {
            socket.kill()
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

        } catch (e: Exception) {
            //TODO show user error
            Log.e("thread", "null pointer")
        }
    }

    override fun socketTimeOut() {
        viewModel.addLogMessage(SocketLogMessage.TIMEOUT)
    }

    override fun ioException() {
        viewModel.addLogMessage(SocketLogMessage.IOEXCEPTION)
    }

    override fun socketException() {
        viewModel.addLogMessage(SocketLogMessage.EXCEPTION)
    }

    override fun dataReceived(data: ByteArray) {
        viewModel.addLogMessage(SocketLogMessage.RECEIVED)
    }

    override fun socketStart() {
        viewModel.addLogMessage(SocketLogMessage.START)
    }

    override fun socketClosed() {
        viewModel.addLogMessage(SocketLogMessage.CLOSED)
    }

    override fun sendPacket(data: ByteArray) {
        viewModel.addLogMessage(SocketLogMessage.SEND)
    }

    private fun sendMessage(message: String) {
        if (::socket.isInitialized) {
            Log.e("send", "try to send $message")
            val byteArray = message.toByteArray()
            socket.addMessageToQue(byteArray)
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

    fun getLocalIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
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
