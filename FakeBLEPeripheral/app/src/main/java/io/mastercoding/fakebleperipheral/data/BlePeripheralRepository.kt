package io.mastercoding.fakebleperipheral.data

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.content.Context
import android.os.ParcelUuid
import android.util.Log
import androidx.annotation.RequiresPermission
import io.mastercoding.fakebleperipheral.BleConstants
import java.util.Random

class BlePeripheralRepository(private val context: Context) {

    private var gattServer: BluetoothGattServer? = null

    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = [
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_ADVERTISE
    ])
    fun startServer(callback: (String) -> Unit) {
        Log.d("REPO", "repo.startServer called")

        val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val adapter = manager.adapter

        gattServer = manager.openGattServer(context, object : BluetoothGattServerCallback() {

            @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
            override fun onCharacteristicReadRequest(
                device: BluetoothDevice,
                requestId: Int,
                offset: Int,
                characteristic: BluetoothGattCharacteristic
            ) {

                val value = "Temp: ${Random().nextInt(30)}°C"

                characteristic.value = value.toByteArray()

                gattServer?.sendResponse(
                    device,
                    requestId,
                    BluetoothGatt.GATT_SUCCESS,
                    0,
                    characteristic.value
                )

                callback(value)
            }
        })

        val service = BluetoothGattService(
            BleConstants.SERVICE_UUID,
            BluetoothGattService.SERVICE_TYPE_PRIMARY
        )

        val characteristic = BluetoothGattCharacteristic(
            BleConstants.CHAR_UUID,
            BluetoothGattCharacteristic.PROPERTY_READ or
                    BluetoothGattCharacteristic.PROPERTY_NOTIFY,
            BluetoothGattCharacteristic.PERMISSION_READ
        )

        service.addCharacteristic(characteristic)
        gattServer?.addService(service)


        val advertiser = adapter.bluetoothLeAdvertiser

        val settings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
            .setConnectable(true)
            .setTimeout(0)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
            .build()

        adapter.name = "Bhau_BLE"
        Log.d("BLE", "Device name = ${adapter.name}")

        val data = AdvertiseData.Builder()
            .setIncludeDeviceName(true)
            .addServiceUuid(ParcelUuid(BleConstants.SERVICE_UUID))
            .build()

        advertiser.startAdvertising(
            settings,
            data,
            object : AdvertiseCallback() {

                override fun onStartSuccess(settingsInEffect: AdvertiseSettings) {
                    android.util.Log.d("BLE", "Advertising started ")
                }

                override fun onStartFailure(errorCode: Int) {
                    android.util.Log.e("BLE", "Advertising failed code=$errorCode")
                }
            }
        )

    }
}