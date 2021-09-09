package com.antonkesy.udptool.messages

import com.antonkesy.udptool.util.getCurrentTimeAsStringDate

enum class SocketLogMessageType {
    CLOSED, START, EXCEPTION, TIMEOUT, IOEXCEPTION
}

data class SocketLogMessage(
    override val title: String,
    override val info: String,
    override val time: String
) :
    ILogMessage {
    constructor(type: SocketLogMessageType, message: String) : this(
        title = when (type) {
            SocketLogMessageType.CLOSED -> "socket closed"
            SocketLogMessageType.START -> "socket start"
            SocketLogMessageType.EXCEPTION -> "socket exception"
            SocketLogMessageType.TIMEOUT -> "socket timeout"
            SocketLogMessageType.IOEXCEPTION -> "socket ioexception"
        },
        info = message,
        time = getCurrentTimeAsStringDate()
    )
}
