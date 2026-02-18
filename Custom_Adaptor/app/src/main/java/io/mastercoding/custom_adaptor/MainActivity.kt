package io.mastercoding.custom_adaptor

import android.os.Bundle
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

        val os=listOf("Android","Windows","macOS","iOs")

        val mylistview: ListView=findViewById(R.id.listView)
        val customAdapter=MyCustomAdapter(this,os)
        mylistview.adapter=customAdapter

    }
}