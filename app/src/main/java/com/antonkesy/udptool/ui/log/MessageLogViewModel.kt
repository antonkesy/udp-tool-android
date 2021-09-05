package com.antonkesy.udptool.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonkesy.udptool.data.DeviceLogMessage
import com.antonkesy.udptool.messages.ILogMessage
import kotlinx.coroutines.launch

class MessageLogViewModel : ViewModel() {


    private val _logMessages = MutableLiveData<List<ILogMessage>>()
    val logMessages: LiveData<List<ILogMessage>> = _logMessages
    fun setLogMessages(newLogs: List<ILogMessage>) {
        _logMessages.value = newLogs
    }

    private val _canSendMessages = MutableLiveData<Boolean>()
    val canSendMessages: LiveData<Boolean> = _canSendMessages
    fun setCanSendMessages(canSendMessages: Boolean) {
        _canSendMessages.value = canSendMessages
    }

    private val _localPort = MutableLiveData<Int>()
    val localPort: LiveData<Int> = _localPort
    fun setLocalPort(newPort: Int) {
        _localPort.value = newPort
    }

    private val _remotePort = MutableLiveData<Int>()
    val remotePort: LiveData<Int> = _remotePort
    fun setRemotePort(newPort: Int) {
        _remotePort.value = newPort
    }

    private val _remoteIP = MutableLiveData<String>()
    val remoteIP: LiveData<String> = _remoteIP
    fun setRemoteIP(newIP: String) {
        _remoteIP.value = newIP
    }

    private val _bufferSize = MutableLiveData<Int>()
    val bufferSize: LiveData<Int> = _bufferSize
    fun setBufferSize(newSize: Int) {
        _bufferSize.value = newSize
    }

    private val _timeOutTime = MutableLiveData<Int>()
    val timeOutTime: LiveData<Int> = _timeOutTime
    fun setTimeOutTime(newSize: Int) {
        _timeOutTime.value = newSize
    }

    private val _messageCoding = MutableLiveData<LogMessageCoding>()
    val messageCoding: LiveData<LogMessageCoding> = _messageCoding
    fun setMessageCoding(newValue: LogMessageCoding) {
        _messageCoding.value = newValue
    }


    private val _isTimeOutTime = MutableLiveData<Boolean>()
    val isTimeOutTime: LiveData<Boolean> = _isTimeOutTime
    fun setIsTimeOutTime(newValue: Boolean) {
        _isTimeOutTime.value = newValue
    }

    private val _isMessage = MutableLiveData<Boolean>()
    val isMessage: LiveData<Boolean> = _isMessage
    fun setIsMessage(newValue: Boolean) {
        _isMessage.value = newValue
    }

    fun generateTestLogMessages() {
        viewModelScope.launch {
            val messages = mutableListOf<ILogMessage>()
            for (i in 1..1000) {
                messages.add(0, DeviceLogMessage("message $i"))
            }
            _logMessages.postValue(messages)
        }
    }

}