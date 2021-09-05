package com.antonkesy.udptool.udp

interface IUDPThreadExceptions {
    fun socketTimeOut()
    fun io()
    fun socket()
}