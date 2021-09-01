package com.antonkesy.udptool.data

import java.util.*

data class DeviceLogMessage(override val message: String, override val time: Long) : ILogMessage {
    constructor(message: String) : this(message = message, Date().time)
}
