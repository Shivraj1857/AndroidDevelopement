package io.mastercoding.viewpagerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var myAdapter: myPagerAdapter

    var tabArray=arrayOf("my App","Coding","Last Advice")
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //ini
        viewPager2=findViewById(R.id.viewPager2)
        viewPager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL

       //add fragment to list
        myAdapter= myPagerAdapter(supportFragmentManager, lifecycle)
        myAdapter.addFragmentToList(Fragment_One())
        myAdapter.addFragmentToList(Fragment_Two())
        myAdapter.addFragmentToList(Fragment_Three())

        //conneting adapter to view pager

        viewPager2.adapter=myAdapter

        //viewpager+tablayout

        tabLayout=findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout,viewPager2){
            tab,position->
            //tab.text="Tab ${position+1}"
            tab.text=tabArray[position]
        }.attach()



    }
}