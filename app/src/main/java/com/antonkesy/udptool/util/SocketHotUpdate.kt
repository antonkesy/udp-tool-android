package com.antonkesy.udptool.util

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.antonkesy.udptool.ui.log.MessageLogViewModel

fun addSocketHotUpdate(
    logViewModel: MessageLogViewModel, lifecycleOwner: LifecycleOwner, createSocket: () -> Unit
) {
    addObserver(
        lifecycleOwner = lifecycleOwner,
        logViewModel = logViewModel,
        createSocket = createSocket
    )
}

fun addObserver(
    lifecycleOwner: LifecycleOwner,
    logViewModel: MessageLogViewModel,
    createSocket: () -> Unit
) {
    val toListenLiveData = listOf(
        logViewModel.isListeningInterval,
        logViewModel.localPort,
        logViewModel.remotePort,
        logViewModel.remoteIP,
        logViewModel.bufferSize,
        logViewModel.timeOutTime,
        logViewModel.messageCoding,
        logViewModel.isTimeOutTime,
        logViewModel.isMessage,
        logViewModel.listenInterval,
        logViewModel.isListening
    )
    for (i in toListenLiveData) {
        i.observe(lifecycleOwner, {
            Log.e("hotswap", "hotswap because of $i")
            createSocket()
        })
    }
}


