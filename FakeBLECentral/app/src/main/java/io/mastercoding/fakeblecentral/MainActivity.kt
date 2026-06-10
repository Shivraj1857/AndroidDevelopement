package io.mastercoding.fakeblecentral

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import io.mastercoding.fakeblecentral.viewmodel.CentralViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: CentralViewModel

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->

            val granted = result.values.all { it }

            if (granted) {
                vm.scan()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[CentralViewModel::class.java]

        val btn = findViewById<Button>(R.id.btnScan)
        val txt = findViewById<TextView>(R.id.txtData)

        btn.setOnClickListener {

            if (hasBlePermissions()) {
                vm.scan()
            } else {
                requestBlePermissions()
            }
        }

        vm.data.observe(this) {
            txt.text = it
        }
    }

    private fun hasBlePermissions(): Boolean {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) == PackageManager.PERMISSION_GRANTED &&

                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) == PackageManager.PERMISSION_GRANTED

        } else {
            true
        }
    }

    private fun requestBlePermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
            )
        }
    }
}