package com.antonkesy.udptool.messages

import java.text.SimpleDateFormat
import java.util.*

data class DeviceLogMessage(
    override val info: String,
    override val time: String,
    val data: ByteArray
) : ILogMessage {
    constructor(info: String, data: ByteArray) : this(
        info = info,
        SimpleDateFormat("HH:mm:ss.SSS").format(Date().time), data
    )

    constructor(info: String) : this(info, ByteArray(0))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceLogMessage

        if (info != other.info) return false
        if (time != other.time) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = info.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }

}
