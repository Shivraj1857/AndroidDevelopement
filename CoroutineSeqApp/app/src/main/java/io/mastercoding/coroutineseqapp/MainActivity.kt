package io.mastercoding.coroutineseqapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //sequitial
//        CoroutineScope(Dispatchers.IO).launch {
//            val one=doSomeThingUseful1()
//            val two=doSomeThingUseful2()
//            val result=one+two
//            Log.v("TAGY","the result is $result")
//        }

        //parallel
        CoroutineScope(Dispatchers.IO).launch {
            Log.v("TAGY","Zal chalu")
            val one=async {doSomeThingUseful1()  }
            val two=async { doSomeThingUseful2() }
            val result=one.await()+two.await()
            Log.v("TAGY","the result is $result")
        }


    }
    suspend fun doSomeThingUseful1():Int{
        delay(5000)
        Log.v("TAGY","Fun1 is done")
        return 11
    }

    suspend fun doSomeThingUseful2():Int{
        delay(7000)
        Log.v("TAGY","Fun2 is done")
        return 8
    }
}
