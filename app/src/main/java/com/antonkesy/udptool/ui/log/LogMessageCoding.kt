package com.antonkesy.udptool.ui.log

import androidx.annotation.StringRes
import com.antonkesy.udptool.R

enum class LogMessageCodingMethode {
    ASCII, HEX
}

sealed class LogMessageCoding(@StringRes val nameId: Int)

object ASCII : LogMessageCoding(R.string.ascii_mode)
object HEX : LogMessageCoding(R.string.hex_mode)
