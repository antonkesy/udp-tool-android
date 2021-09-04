package com.antonkesy.udptool.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.lifecycle.LifecycleOwner
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

internal fun loadSavedData(viewModel: MessageLogViewModel, dataStore: DataStore<Preferences>) {
    runBlocking {
        viewModel.setLocalPort(getLocalPort(dataStore))
    }
}

internal fun observeToSaveData(
    lifecycleOwner: LifecycleOwner,
    viewModel: MessageLogViewModel,
    dataStore: DataStore<Preferences>
) {
    viewModel.localPort.observe(lifecycleOwner, {
        runBlocking {
            setLocalPort(it, dataStore)
        }
    })
}

private suspend fun getInt(
    key: Preferences.Key<Int>,
    dataStore: DataStore<Preferences>, default: Int = 0
): Int {
    var value = default

    val exampleCounterFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[key] ?: default
        }

    exampleCounterFlow.first {
        value = it
        true
    }
    return value
}

private suspend fun setInt(
    newValue: Int,
    key: Preferences.Key<Int>,
    dataStore: DataStore<Preferences>
) {
    dataStore.edit { settings ->
        settings[key] = newValue
    }
}

private suspend fun getLocalPort(dataStore: DataStore<Preferences>): Int {
    return getInt(LOCAL_PORT, dataStore)
}

private suspend fun setLocalPort(newValue: Int, dataStore: DataStore<Preferences>) {
    setInt(newValue, LOCAL_PORT, dataStore)
}
