package io.mastercoding.viewwithcompose1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val composeLayout=findViewById<ComposeView>(R.id.compose_layout)
        composeLayout.setContent {
            sayRamRam("Ram Ram")
        }

    }
}
@Composable
fun sayRamRam(name: String){
    Text(text = "$name Bhau")
}