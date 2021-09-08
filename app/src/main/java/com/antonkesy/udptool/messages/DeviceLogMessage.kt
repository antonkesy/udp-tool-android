package com.antonkesy.udptool.messages

import java.text.SimpleDateFormat
import java.util.*

data class DeviceLogMessage(
    override val title: String,
    override val info: String,
    override val time: String,
    val data: ByteArray
) : ILogMessage {
    constructor(title: String, info: String, data: ByteArray) : this(
        title = title,
        info = info,
        time = SimpleDateFormat("HH:mm:ss.SSS").format(Date().time), data = data
    )

    constructor(title: String, info: String) : this(title = title, info = info, data = ByteArray(0))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceLogMessage

        if (title != other.title) return false
        if (time != other.time) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }

}
