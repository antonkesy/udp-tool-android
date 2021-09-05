package com.antonkesy.udptool.util

import com.antonkesy.udptool.ui.log.MessageLogViewModel
import java.net.Inet4Address

fun isLegalIP(value: String): Boolean {
    try {
        return Inet4Address.getByName(value).hostAddress.equals(value)
    } catch (e: Exception) {
    }
    return false
}

fun setNewRemoteIP(value: String, logViewModel: MessageLogViewModel): Boolean {
    if (isLegalIP(value = value)) {
        logViewModel.setRemoteIP(value)
        return false
    }
    return true
}

