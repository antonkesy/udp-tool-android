package com.antonkesy.udptool.udp

import com.antonkesy.udptool.ui.log.MessageLogViewModel

fun isLegalIP(value: String): Boolean {
    //TODO
    try {
        if (Integer.parseInt(value) in 0..65_535) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false
}


fun getIP(value: String): String {
    //TODO
    return value
}

fun setNewRemoteIP(value: String, logViewModel: MessageLogViewModel): Boolean {
    if (isLegalPort(value = value)) {
        logViewModel.setRemoteIP(getIP(value = value))
        return false
    }
    return true
}

