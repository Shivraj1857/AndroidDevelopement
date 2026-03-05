package io.mastercoding.viewpagerapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class myPagerAdapter(fm: FragmentManager,lc: Lifecycle): FragmentStateAdapter(fm,lc) {

    var fragmentArrayList: ArrayList<Fragment> = ArrayList()
    fun addFragmentToList(fragment: Fragment){
        fragmentArrayList.add(fragment)
    }
    override fun createFragment(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }

    override fun getItemCount(): Int {
        return fragmentArrayList.size

    }
}