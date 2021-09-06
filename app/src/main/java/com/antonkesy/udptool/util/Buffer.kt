package com.antonkesy.udptool.util

fun isStringLegalBufferSize(input: String): Boolean {
    try {
        if (Integer.parseInt(input) in 1..Int.MAX_VALUE) {
            return true
        }
    } catch (e: NumberFormatException) {
    }
    return false;
}