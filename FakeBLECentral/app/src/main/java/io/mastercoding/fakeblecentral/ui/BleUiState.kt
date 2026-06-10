package io.mastercoding.fakebleperipheral.ui

data class BleUiState(
    val connectionState: String = "Disconnected",
    val temperature: String = "--"
)