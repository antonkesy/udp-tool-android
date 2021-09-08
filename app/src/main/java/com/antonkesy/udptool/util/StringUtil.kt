package com.antonkesy.udptool.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTimeAsStringDate(): String {
    return SimpleDateFormat("HH:mm:ss.SSS").format(Date().time)
}

fun getDataAsASCIIString(data: ByteArray): String {
    val result = StringBuilder()
    for (b in data) {
        result.append(b.toInt().toChar())
    }
    return result.toString()
}

fun getDataAsHexString(data: ByteArray): String {
    val result = StringBuilder()
    for (b in data) {
        result.append(Integer.toHexString(b.toInt()))
    }
    return result.toString()
}