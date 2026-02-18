package io.mastercoding.explicit_intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var myButton: Button
    lateinit var webButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        myButton=findViewById(R.id.gotoNextBtn)
        myButton.setOnClickListener {
            val explicitIntent= Intent(this, secondActivity::class.java)
            explicitIntent.putExtra("myName","Shiva")
            startActivity(explicitIntent)
        }
        webButton=findViewById(R.id.openweb)
        webButton.setOnClickListener {
            val implicitIntent= Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/")
            )
            startActivity(implicitIntent)
        }

    }
}