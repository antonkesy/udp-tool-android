package com.antonkesy.udptool.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.antonkesy.udptool.ui.log.ASCII
import com.antonkesy.udptool.ui.log.HEX
import com.antonkesy.udptool.ui.log.LogMessageCoding
import com.antonkesy.udptool.ui.log.MessageLogViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val LOCAL_PORT = intPreferencesKey("local_port")
private val REMOTE_PORT = intPreferencesKey("remote_port")
private val REMOTE_IP = stringPreferencesKey("remote_ip")
private val TIMEOUT_TIME = intPreferencesKey("timeout_time")
private val IS_TIMEOUT = booleanPreferencesKey("isTimeout")
private val IS_MESSAGE = booleanPreferencesKey("isMessage")
private val BUFFER_SIZE = intPreferencesKey("buffer_size")
private val MESSAGE_CODING = intPreferencesKey("message_coding")
private val IS_LISTENING = booleanPreferencesKey("is_listening")
private val IS_LISTENING_INTERVAL = booleanPreferencesKey("is_listening_interval")
private val LISTENING_INTERVAL = intPreferencesKey("listening_interval")

internal fun loadSavedData(viewModel: MessageLogViewModel, dataStore: DataStore<Preferences>) {
    runBlocking {
        with(viewModel) {
            setLocalPort(getLocalPort(dataStore))
            setRemotePort(getRemotePort(dataStore))
            setRemoteIP(getRemoteIP(dataStore))
            setTimeOutTime(getTimeOutTime(dataStore))
            setIsTimeOutTime(getIsTimeOut(dataStore))
            setIsMessage(getIsMessage(dataStore))
            setBufferSize(getBufferSize(dataStore))
            setMessageCoding(getMessageCoding(dataStore))
            setIsListening(getIsListening(dataStore))
            setIsListeningInterval(getIsListeningInterval(dataStore))
            setListenInterval(getListeningInterval(dataStore))
        }
    }
}

internal fun observeToSaveData(
    lifecycleOwner: LifecycleOwner,
    viewModel: MessageLogViewModel,
    dataStore: DataStore<Preferences>
) {
    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.localPort,
        {
            runBlocking {
                setLocalPort(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.remotePort,
        {
            runBlocking {
                setRemotePort(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.remoteIP,
        {
            runBlocking {
                setRemoteIP(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.timeOutTime,
        {
            runBlocking {
                setTimeOutTime(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.isTimeOutTime,
        {
            runBlocking {
                setIsTimeOut(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.isMessage,
        {
            runBlocking {
                setIsMessage(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.bufferSize,
        {
            runBlocking {
                setBufferSize(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.messageCoding,
        {
            runBlocking {
                setMessageCoding(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.listenInterval,
        {
            runBlocking {
                setListeningInterval(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.isListening,
        {
            runBlocking {
                setIsListening(it, dataStore)
            }
        }
    )

    observeData(
        lifecycleOwner = lifecycleOwner,
        liveData = viewModel.isListeningInterval,
        {
            runBlocking {
                setIsListeningInterval(it, dataStore)
            }
        }
    )
}

private fun <T> observeData(
    lifecycleOwner: LifecycleOwner,
    liveData: LiveData<T>,
    setFunction: (T) -> Unit,
) {
    liveData.observe(lifecycleOwner, {
        setFunction(it)
    })
}

private suspend fun <T> getData(
    key: Preferences.Key<T>,
    dataStore: DataStore<Preferences>, default: T
): T {
    var value = default

    val exampleCounterFlow: Flow<T> = dataStore.data
        .map { preferences ->
            preferences[key] ?: default
        }

    exampleCounterFlow.first {
        value = it
        true
    }
    return value
}

private suspend fun <T> setData(
    newValue: T,
    key: Preferences.Key<T>,
    dataStore: DataStore<Preferences>
) {
    dataStore.edit { settings ->
        settings[key] = newValue
    }
}

private suspend fun getLocalPort(dataStore: DataStore<Preferences>): Int {
    return getData(LOCAL_PORT, dataStore, 0)
}

private suspend fun setLocalPort(newValue: Int, dataStore: DataStore<Preferences>) {
    setData(newValue, LOCAL_PORT, dataStore)
}

private suspend fun getRemotePort(dataStore: DataStore<Preferences>): Int {
    return getData(REMOTE_PORT, dataStore, 0)
}

private suspend fun setRemotePort(newValue: Int, dataStore: DataStore<Preferences>) {
    setData(newValue, REMOTE_PORT, dataStore)
}

private suspend fun getTimeOutTime(dataStore: DataStore<Preferences>): Int {
    return getData(TIMEOUT_TIME, dataStore, 0)
}

private suspend fun setTimeOutTime(newValue: Int, dataStore: DataStore<Preferences>) {
    setData(newValue, TIMEOUT_TIME, dataStore)
}

private suspend fun getBufferSize(dataStore: DataStore<Preferences>): Int {
    return getData(BUFFER_SIZE, dataStore, 0)
}

private suspend fun setBufferSize(newValue: Int, dataStore: DataStore<Preferences>) {
    setData(newValue, BUFFER_SIZE, dataStore)
}

private suspend fun getMessageCoding(dataStore: DataStore<Preferences>): LogMessageCoding {
    return when (getData(MESSAGE_CODING, dataStore, 0)) {
        1 -> ASCII
        2 -> HEX
        else -> ASCII
    }
}

private suspend fun setMessageCoding(
    newValue: LogMessageCoding,
    dataStore: DataStore<Preferences>
) {
    setData(
        when (newValue) {
            ASCII -> 1
            HEX -> 2
        }, MESSAGE_CODING, dataStore
    )
}

private suspend fun getRemoteIP(dataStore: DataStore<Preferences>): String {
    return getData(REMOTE_IP, dataStore, "0.0.0.0")
}

private suspend fun setRemoteIP(newValue: String, dataStore: DataStore<Preferences>) {
    setData(newValue, REMOTE_IP, dataStore)
}

private suspend fun getIsTimeOut(dataStore: DataStore<Preferences>): Boolean {
    return getData(IS_TIMEOUT, dataStore, false)
}

private suspend fun setIsTimeOut(newValue: Boolean, dataStore: DataStore<Preferences>) {
    setData(newValue, IS_TIMEOUT, dataStore)
}

private suspend fun getIsMessage(dataStore: DataStore<Preferences>): Boolean {
    return getData(IS_MESSAGE, dataStore, false)
}

private suspend fun setIsMessage(newValue: Boolean, dataStore: DataStore<Preferences>) {
    setData(newValue, IS_MESSAGE, dataStore)
}

private suspend fun getIsListening(dataStore: DataStore<Preferences>): Boolean {
    return getData(IS_LISTENING, dataStore, false)
}

private suspend fun setIsListening(newValue: Boolean, dataStore: DataStore<Preferences>) {
    setData(newValue, IS_LISTENING, dataStore)
}

private suspend fun getIsListeningInterval(dataStore: DataStore<Preferences>): Boolean {
    return getData(IS_LISTENING_INTERVAL, dataStore, false)
}

private suspend fun setIsListeningInterval(newValue: Boolean, dataStore: DataStore<Preferences>) {
    setData(newValue, IS_LISTENING_INTERVAL, dataStore)
}

private suspend fun getListeningInterval(dataStore: DataStore<Preferences>): Int {
    return getData(LISTENING_INTERVAL, dataStore, 0)
}

private suspend fun setListeningInterval(newValue: Int, dataStore: DataStore<Preferences>) {
    setData(newValue, LISTENING_INTERVAL, dataStore)
}