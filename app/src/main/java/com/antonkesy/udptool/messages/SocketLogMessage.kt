package com.antonkesy.udptool.messages

import com.antonkesy.udptool.util.getCurrentTimeAsStringDate

sealed class SocketLogMessage(
    override val title: String,
    override val info: String,
    override val time: String
) :
    ILogMessage {
            constructor(title: String, message: String) : this(
            title = title,
            info = message,
        time = getCurrentTimeAsStringDate()
    )

    object CLOSED : SocketLogMessage("socket closed", "")
    object START : SocketLogMessage("socket start", "")
    object SEND : SocketLogMessage("socket send", "")
    object RECEIVED : SocketLogMessage("socket received", "")
    object EXCEPTION : SocketLogMessage("socket exception", "")
    object TIMEOUT : SocketLogMessage("socket timeout", "")
    object IOEXCEPTION : SocketLogMessage("socket ioexception", "")
}
