package io.mastercoding.fakeblecentral.ui

data class BleUiState(
    val connectionState: String = "Disconnected",
    val deviceInfo: DeviceInfo = DeviceInfo()
)
data class DeviceInfo(
    val manufacturer: String = "",
    val firmware: String = ""
)