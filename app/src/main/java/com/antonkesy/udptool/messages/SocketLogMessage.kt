package com.antonkesy.udptool.messages

import java.text.SimpleDateFormat
import java.util.*

sealed class SocketLogMessage(override val info: String, override val time: String) :
    ILogMessage {
    constructor(message: String) : this(
        info = message,
        SimpleDateFormat("HH:mm:ss.SSS").format(Date().time)
    )

    object CLOSED : SocketLogMessage("socket closed")
    object START : SocketLogMessage("socket start")
    object SEND : SocketLogMessage("socket send")
    object RECEIVED : SocketLogMessage("socket received")
    object EXCEPTION : SocketLogMessage("socket exception")
    object TIMEOUT : SocketLogMessage("socket timeout")
    object IOEXCEPTION : SocketLogMessage("socket ioexception")
}
