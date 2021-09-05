package com.antonkesy.udptool.util

import com.antonkesy.udptool.ui.log.MessageLogViewModel

fun isLegalPort(value: String): Boolean {
    try {
        if (Integer.parseInt(value) in 0..65_535) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false
}

fun getPort(value: String): Int {
    return Integer.parseInt(value)
}

fun setNewLocalPort(value: String, logViewModel: MessageLogViewModel): Boolean {
    if (isLegalPort(value = value)) {
        logViewModel.setLocalPort(getPort(value = value))
        return false
    }
    return true
}

fun setNewRemotePort(value: String, logViewModel: MessageLogViewModel): Boolean {
    if (isLegalPort(value = value)) {
        logViewModel.setRemotePort(getPort(value = value))
        return false
    }
    return true
}
