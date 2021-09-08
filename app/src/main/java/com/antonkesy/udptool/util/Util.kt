package com.antonkesy.udptool.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTimeAsStringDate(): String {
    return SimpleDateFormat("HH:mm:ss.SSS").format(Date().time)
}