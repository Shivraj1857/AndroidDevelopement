package io.mastercoding.fakeblecentral

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.mastercoding.fakeblecentral.ui.viewmodel.BleViewModel
import io.mastercoding.fakeblecentral.viewmodel.CentralViewModel
import kotlinx.coroutines.launch
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

    private lateinit var vm: CentralViewModel
    private val viewModel: BleViewModel by viewModels()
    private lateinit var connectionText: TextView
    private lateinit var txt: TextView

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
        txt = findViewById<TextView>(R.id.txtData)
        connectionText = findViewById(R.id.connectionText)

        observeUiState()


        btn.setOnClickListener {

            if (hasBlePermissions()) {
                vm.scan()
            } else {
                requestBlePermissions()
            }

            viewModel.startConnection(this)

        }

        vm.data.observe(this) {
            Log.d("CentralVM", "observer: $it")
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

    private fun observeUiState() {

        lifecycleScope.launch {

            viewModel.uiState.collect { state ->

                Log.d("state", "conn state: ${state.connectionState}")
                connectionText.text = state.connectionState
                
            }
        }
    }
}