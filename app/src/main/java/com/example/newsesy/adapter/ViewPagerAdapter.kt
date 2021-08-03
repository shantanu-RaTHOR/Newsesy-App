package com.example.newsesy.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsesy.ui.ViewpagerFragments.*
import com.example.newsesy.ui.home.HomeFragment
class ViewPagerAdapter(private val myContext: Context,internal var totalTabs: Int,
                       private  val fa: FragmentActivity
) :FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 9
    }

    override fun createFragment(position: Int): Fragment
    {
        when(position)
        {
            0-> return  Category("entertainment")
            1-> return   CustomCategory("sports")
            2-> return   CustomCategory("tech")
            3-> return  Category("science")
            4-> return  Category("business")
            5-> return  CustomCategory("movies")
            6-> return  CustomCategory("automobiles")
            7-> return  CustomCategory("politics")
            8-> return   Category("health")
            else-> return HomeFragment()
        }
    }


}