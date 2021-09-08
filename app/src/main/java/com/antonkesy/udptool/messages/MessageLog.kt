package com.antonkesy.udptool.messages

import com.antonkesy.udptool.util.getCurrentTimeAsStringDate

data class MessageLog(
    override val title: String,
    override val info: String,
    override val time: String,
    val isSend: Boolean,
    val data: ByteArray
) : ILogMessage {
    constructor(isSend: Boolean, data: ByteArray) : this(
        title = if (isSend) "send message" else "received message",
        info = "socket message",
        time = getCurrentTimeAsStringDate(),
        isSend = isSend,
        data = data
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageLog

        if (title != other.title) return false
        if (info != other.info) return false
        if (time != other.time) return false
        if (isSend != other.isSend) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + info.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + isSend.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}
