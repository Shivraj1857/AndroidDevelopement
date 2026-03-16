package io.mastercoding.coroutineapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import io.mastercoding.coroutineapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var counter:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btn1.setOnClickListener {
            binding.text2.text=counter++.toString()
        }

        binding.btn2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                DownloadBigFileFromNet()
            }
        }



    }
}

private fun MainActivity.DownloadBigFileFromNet() {
    for (i in 1..100000) {
        Log.i("Tagy", "Downloading $i in ${Thread.currentThread().name}")
    }
}


    //as apan karu nako asha case madhi coroutine vaparav karan
    //jevha apan start download ch btn dabu theva te 100000 download hoi paryat apal
    // counter btn one chalanar nahi karan apala to mainthread thread thikade download karitahe
//asha veles apan couroutne vaparato

