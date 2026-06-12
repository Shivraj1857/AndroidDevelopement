package io.mastercoding.fakeblecentral.data

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.mastercoding.fakeblecentral.BleConstants
import io.mastercoding.fakeblecentral.ui.DeviceInfo
import java.util.UUID

class BleCentralRepository(
    private val context: Context
) {

    private val adapter = BluetoothAdapter.getDefaultAdapter()
    private var gatt: BluetoothGatt? = null
    private var isServiceReady = false
    private var manufacturer: String = ""
    private var firmware: String = ""
    private var deviceInfoCallback: ((DeviceInfo) -> Unit)? = null

    @SuppressLint("MissingPermission")
    fun readDeviceInfo(callback: (DeviceInfo) -> Unit) {
        deviceInfoCallback = callback
        Log.d("CENTRAL", "repo deveci info")

        Log.d("CENTRAL", "readDeviceInfo called")
        Log.d("CENTRAL", "gatt = $gatt")

        val gatt = this.gatt

        if (gatt == null || !isServiceReady) {
            callback(
                DeviceInfo(
                    manufacturer = "Device not ready",
                    firmware = ""
                )
            )
            return
        }
        manufacturer = ""
        firmware = ""


        Log.d("CENTRAL", "Services count = ${gatt?.services?.size}")
        val service = gatt.getService(
            UUID.fromString("0000180A-0000-1000-8000-00805F9B34FB")
        )

        if (service == null) {
            callback(
                DeviceInfo(
                    manufacturer = "Service not found",
                    firmware = ""
                )
            )
            return
        }

        val manufacturerChar = service.getCharacteristic(
            UUID.fromString("00002A29-0000-1000-8000-00805F9B34FB")
        )

        val firmwareChar = service.getCharacteristic(
            UUID.fromString("00002A26-0000-1000-8000-00805F9B34FB")
        )

        // ⚠️ BLE RULE: read ONE at a time
        if (manufacturerChar != null) {
            gatt.readCharacteristic(manufacturerChar)
        }
    }

    @SuppressLint("MissingPermission")
    fun connectAndRead(context: Context, callback: (String) -> Unit){

        val scanner = adapter.bluetoothLeScanner

        val scanCallback = object : ScanCallback() {

            override fun onScanResult(
                callbackType: Int,
                result: ScanResult
            ) {

                val device = result.device

                Log.d("CENTRAL", "Found: ${device.name} ${device.address}")

                if (device.name != "Bhau_BLE") {
                    return
                }

                scanner.stopScan(this)
                device.connectGatt(
                    context,
                    false,
                    object : BluetoothGattCallback(){

                        override fun onConnectionStateChange(
                            gatt: BluetoothGatt,
                            status: Int,
                            newState: Int
                        ){
                            Log.d(
                                "CENTRAL",
                                "status=$status newState=$newState")
                           // this@BleCentralRepository.gatt = gatt


                            val name = gatt.device.name ?: "Unknown"

                            when (newState) {

                                BluetoothProfile.STATE_CONNECTED -> {
                                    callback("Connection: Connected to $name")
                                    gatt.discoverServices()
                                }

                                BluetoothProfile.STATE_DISCONNECTED -> {
                                    callback("Connection: Disconnected")
                                    gatt.close()
                                }
                            }
                        }

                        override fun onServicesDiscovered(
                            gatt: BluetoothGatt,
                            status: Int
                        ) {

                            Log.d("CENTRAL", "Services discovered")

                            this@BleCentralRepository.gatt = gatt
                            isServiceReady = true

                            manufacturer = ""
                            firmware = ""

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

                            if (status != BluetoothGatt.GATT_SUCCESS) return

                            val value = characteristic.value.toString(Charsets.UTF_8)

                            when (characteristic.uuid.toString()) {

                                "00002A29-0000-1000-8000-00805F9B34FB" -> {

                                    manufacturer = value

                                    val firmwareChar = gatt
                                        .getService(UUID.fromString("0000180A-0000-1000-8000-00805F9B34FB"))
                                        ?.getCharacteristic(UUID.fromString("00002A26-0000-1000-8000-00805F9B34FB"))

                                    firmwareChar?.let {
                                        gatt.readCharacteristic(it)
                                    }
                                }

                                "00002A26-0000-1000-8000-00805F9B34FB" -> {

                                    firmware = value

                                    // NOW SEND BOTH TO UI TOGETHER
                                    deviceInfoCallback?.invoke(
                                        DeviceInfo(
                                            manufacturer = manufacturer,
                                            firmware = firmware
                                        )
                                    )
                                }
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