package com.antonkesy.udptool.util

import com.antonkesy.udptool.ui.log.MessageLogViewModel
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

fun isLegalIP(value: String): Boolean {
    try {
        val hostAddress = Inet4Address.getByName(value).hostAddress ?: return false
        return hostAddress == value
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