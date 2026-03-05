package io.mastercoding.fragmentapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btn1: Button=findViewById(R.id.frag1)
        var btn2: Button=findViewById(R.id.frag2)

        btn1.setOnClickListener {
            val fragment1: Fragment_One= Fragment_One()
            fragmentLoad(fragment1)
        }
        btn2.setOnClickListener {
            val fragment2: Fragment_Two= Fragment_Two()
            fragmentLoad(fragment2)
        }
    }

    fun fragmentLoad(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTrasaction: FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTrasaction.replace(R.id.framelayout,fragment)
        fragmentTrasaction.commit()

    }
}