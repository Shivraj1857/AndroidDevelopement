package io.mastercoding.explicit_intent

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class secondActivity : AppCompatActivity() {
    lateinit var mytext: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
       mytext=findViewById(R.id.myTextView)
        val receivedData=intent.getStringExtra("myName")

        mytext.text="Welcome $receivedData to second activity"

    }
}