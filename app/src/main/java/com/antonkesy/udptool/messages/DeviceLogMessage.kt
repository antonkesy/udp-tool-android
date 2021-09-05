package com.antonkesy.udptool.messages

import java.text.SimpleDateFormat
import java.util.*

data class DeviceLogMessage(override val message: String, override val time: String) : ILogMessage {
    constructor(message: String) : this(
        message = message,
        SimpleDateFormat("HH:mm:ss.SSS").format(Date().time)
    )
}
