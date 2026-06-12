package io.mastercoding.fakeblecentral.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import io.mastercoding.fakeblecentral.data.BleCentralRepository
import io.mastercoding.fakeblecentral.ui.BleUiState
import io.mastercoding.fakeblecentral.ui.DeviceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BleViewModel : ViewModel() {

    private var repository: BleCentralRepository? = null
    private val _uiState = MutableStateFlow(BleUiState())
    val uiState = _uiState.asStateFlow()

    fun startConnection(context: Context) {

        if (repository == null) {
            repository = BleCentralRepository(context)
        }

        repository?.connectAndRead(context) { value ->

            when {
                value.startsWith("Connection: Connected") -> {
                    _uiState.value = _uiState.value.copy(
                        connectionState = "Connected"
                    )
                }

                value.startsWith("Connection: Disconnected") -> {
                    _uiState.value = _uiState.value.copy(
                        connectionState = "Disconnected"
                    )
                }
            }
        }
    }
    fun readDeviceInfo() {
        Log.d("CENTRAL", "ViewModel readDeviceInfo")
        repository?.readDeviceInfo { info: DeviceInfo ->

            _uiState.value = _uiState.value.copy(
                deviceInfo = info

            )
        }
    }
}