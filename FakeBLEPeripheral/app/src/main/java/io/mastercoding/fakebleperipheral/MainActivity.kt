package io.mastercoding.fakebleperipheral

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.mastercoding.fakebleperipheral.viewmodel.PeripheralViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var vm: PeripheralViewModel

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

            val granted = result.values.all { it }

            if (granted) {
                @SuppressLint("MissingPermission")
                vm.startServer()
            }
        }

    private fun hasBlePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            checkSelfPermission(android.Manifest.permission.BLUETOOTH_CONNECT) ==
                    android.content.pm.PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(android.Manifest.permission.BLUETOOTH_ADVERTISE) ==
                    android.content.pm.PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[PeripheralViewModel::class.java]

        val btn = findViewById<Button>(R.id.btnStart)
        val txt = findViewById<TextView>(R.id.txtData)

        btn.setOnClickListener {
            Log.d("BTN", "Start button clicked")
            if (hasBlePermission()) {
                @SuppressLint("MissingPermission")
                vm.startServer()
            } else {
                requestBlePermissions()
            }
        }

        vm.data.observe(this) { value ->
            txt.text = value
        }
    }

    private fun requestBlePermissions() {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_ADVERTISE
            )
        )
    }
}