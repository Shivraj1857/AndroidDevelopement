package io.mastercoding.fakebleperipheral.viewmodel

import android.Manifest
import android.app.Application
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mastercoding.fakebleperipheral.data.BlePeripheralRepository

class PeripheralViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = BlePeripheralRepository(app)

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    @RequiresPermission(allOf = [
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_ADVERTISE
    ])
    //@SuppressLint("MissingPermission")
    fun startServer() {
        Log.d("VM", "startServer called")
        try {
            Log.d("PERIPHERAL", "Starting server")

            repo.startServer {
                _data.postValue(it)
            }
        } catch (_: SecurityException) {
            _data.postValue("Permission denied")
        }
    }
}