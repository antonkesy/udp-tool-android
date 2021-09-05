package com.antonkesy.udptool.udp

interface ISocketResponses {
    fun socketTimeOut()
    fun ioException()
    fun socketException()
    fun dataReceived(data: ByteArray)
}