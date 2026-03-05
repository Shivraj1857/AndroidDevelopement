package io.mastercoding.broadcast_receiver_intent_filter

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var airplanemodeReicever: AirphoneModeReicever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        var intentfilter: IntentFilter= IntentFilter("android.intent.action.AIRPLANE_MODE")
         airplanemodeReicever= AirphoneModeReicever()
        registerReceiver(airplanemodeReicever,intentfilter)

    }

    override fun onDestroy() {
        unregisterReceiver(airplanemodeReicever)
        super.onDestroy()
    }
}

