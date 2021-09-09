package com.antonkesy.udptool.udp

enum class TimeOutReason {
    RECEIVE_TIMEOUT, SEND_RESPONSE_TIMEOUT
}

interface ISocketResponses {
    fun socketTimeOut(reason: TimeOutReason)
    fun ioException()
    fun socketException()
    fun dataReceived(data: ByteArray)
    fun socketStart()
    fun socketClosed()
    fun sendPacket(data: ByteArray)
}