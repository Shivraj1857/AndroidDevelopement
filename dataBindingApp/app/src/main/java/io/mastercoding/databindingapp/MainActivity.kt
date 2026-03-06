package io.mastercoding.databindingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.mastercoding.databindingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        //binding.first.text="Ram Ram Bhau"

        //class chya object banun access
        //var v1= Vehicle("2012","BMW")
       // binding.first.text=v1.name

        //yethe fakt
        //binding.myVariable=v1

        //hnding btn
//        binding.btn.setOnClickListener {
//            Toast.makeText(this,"My btn is clicked", Toast.LENGTH_LONG).show()
//        }

        //another option

        //binding.clickme= VehicleClickHandler(this)


        var v1= Vehicle()
        v1.name= "Mercedes"
        v1.modelyear= "2020"
        binding.myVehicle=v1

        binding.clickme= VehicleClickHandler(this)


    }
}