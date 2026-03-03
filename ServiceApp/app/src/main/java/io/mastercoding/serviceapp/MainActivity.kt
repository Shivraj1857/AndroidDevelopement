package io.mastercoding.serviceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        var textView: TextView=findViewById(R.id.textView1)
        var startBtn: Button=findViewById(R.id.btn1)
        var stopBtn: Button=findViewById(R.id.btn2)

        var hii: Button=findViewById(R.id.btn4)
        startBtn.setOnClickListener {
            var startIntent= Intent(applicationContext, myService::class.java)
            startService(startIntent)
        }
        stopBtn.setOnClickListener {
            var startIntent= Intent(applicationContext, myService::class.java)
            stopService(startIntent)


        }
        hii.setOnClickListener {
            Toast.makeText(this,"hiii", Toast.LENGTH_SHORT).show()
        }




    }
}