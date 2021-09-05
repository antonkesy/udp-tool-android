package com.antonkesy.udptool.util

fun isTimeOutLegal(value: String): Boolean {
    try {
        if (Integer.parseInt(value) in 0..Int.MAX_VALUE) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false
}