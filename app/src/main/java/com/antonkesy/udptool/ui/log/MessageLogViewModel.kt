package com.antonkesy.udptool.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonkesy.udptool.data.DeviceLogMessage
import com.antonkesy.udptool.data.ILogMessage
import kotlinx.coroutines.launch

class MessageLogViewModel : ViewModel() {


    private val _logMessages = MutableLiveData<List<ILogMessage>>()
    val logMessages: LiveData<List<ILogMessage>> = _logMessages
    fun setLogMessages(newLogs: List<ILogMessage>) {
        _logMessages.value = newLogs
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