package io.mastercoding.adapter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //ini
        val adap=findViewById<ListView>(R.id.list)

        val os=arrayOf("ios","window","macos","android")

        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,os)
        adap.adapter=adapter
    }
}