package io.mastercoding.planetsapp

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

        val ListView=findViewById<ListView>(R.id.listView)

        //data
        val planet1= planet("Mercury", "0 moons", R.drawable.mercury)
        val planet2= planet("Venus", "0 moons", R.drawable.venus)
        val planet3= planet("Earth", "1 moons", R.drawable.earth)
        val planet4= planet("Mars", "2 moons", R.drawable.mars)
        val planet5= planet("Jupiter", "79 moons", R.drawable.jupiter)
        val planet6= planet("Saturn", "83 moons", R.drawable.saturn)
        val planet7= planet("Uranus", "27 moons", R.drawable.uranus)
        val planet8= planet("Neptune", "14 moons", R.drawable.neptune)

        var planetsList= ArrayList<planet>()
        planetsList.add(planet1)
        planetsList.add(planet2)
        planetsList.add(planet3)
        planetsList.add(planet4)
        planetsList.add(planet5)
        planetsList.add(planet6)
        planetsList.add(planet7)
        planetsList.add(planet8)

        //adapter kar

        var myAdapter= MycustomAdapter(this,planetsList)
        ListView.adapter=myAdapter










    }
}