package io.mastercoding.fakeblecentral.data

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import io.mastercoding.fakeblecentral.BleConstants

class BleCentralRepository(
    private val context: Context
) {

    private val adapter = BluetoothAdapter.getDefaultAdapter()

    @SuppressLint("MissingPermission")
    fun connectAndRead(callback: (String) -> Unit) {

        val scanner = adapter.bluetoothLeScanner

        val scanCallback = object : ScanCallback() {

            override fun onScanResult(
                callbackType: Int,
                result: ScanResult
            ) {

                val device = result.device

                Log.d("CENTRAL", "Found: ${device.name} ${device.address}")

                // ✅ FIX ADD HERE (FILTER)
                if (device.name != "FakeBLEPeripheral") {
                    return
                }

               // stop scan ONLY after correct device found
                scanner.stopScan(this)

//                val device = result.device
//
//                Log.d("CENTRAL", "Found: ${device.name} ${device.address}")
//
//                // ✅ REAL FILTER (SERVICE UUID BASED)
//                val hasService = result.scanRecord?.serviceUuids?.any {
//                    it.uuid == BleConstants.SERVICE_UUID
//                } == true
//
//                if (!hasService) return
//
//                scanner.stopScan(this)


                device.connectGatt(
                    context,
                    false,
                    object : BluetoothGattCallback(){

                        override fun onConnectionStateChange(
                            gatt: BluetoothGatt,
                            status: Int,
                            newState: Int
                        ) {

                            val deviceName = gatt.device.name ?: "Unknown"
                            val deviceAddress = gatt.device.address

                            when (newState) {

                                BluetoothProfile.STATE_CONNECTED -> {
                                    Log.d("CENTRAL", "Connected to $deviceName ($deviceAddress)")
                                    callback("Connection: Connected to $deviceName")

                                    gatt.discoverServices()
                                }

                                BluetoothProfile.STATE_DISCONNECTED -> {
                                    Log.d("CENTRAL", "Disconnected from $deviceName")
                                    callback("Connection: Disconnected from $deviceName")

                                    gatt.close()
                                }
                            }

                            if (status != BluetoothGatt.GATT_SUCCESS) {
                                Log.e("CENTRAL", "Connection error status=$status")
                                callback("Connection Error: $status")
                            }
                        }

                        override fun onServicesDiscovered(
                            gatt: BluetoothGatt,
                            status: Int
                        ) {

                            val service = gatt.getService(BleConstants.SERVICE_UUID)

                            if (service == null) {
                                Log.e("CENTRAL", "Service NOT FOUND")
                                return
                            }

                            val characteristic =
                                service?.getCharacteristic(
                                    BleConstants.CHAR_UUID
                                )

                            if (characteristic != null) {
                                gatt.readCharacteristic(characteristic)
                            }
                        }

                        override fun onCharacteristicRead(
                            gatt: BluetoothGatt,
                            characteristic: BluetoothGattCharacteristic,
                            status: Int
                        ) {

                            if (status ==
                                BluetoothGatt.GATT_SUCCESS
                            ) {

                                val value =
                                    characteristic.value.toString(
                                        Charsets.UTF_8
                                    )

                                callback(value)
                            }
                        }
                    }
                )
            }
            override fun onScanFailed(errorCode: Int) {
                Log.e("CENTRAL", "Scan Failed: $errorCode")
            }
        }

        scanner.startScan(scanCallback)
    }
}