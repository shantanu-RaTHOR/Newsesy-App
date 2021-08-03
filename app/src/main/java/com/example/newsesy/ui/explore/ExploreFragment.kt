package com.example.newsesy.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.newsesy.R
import com.example.newsesy.adapter.ViewPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ExploreFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        val title: TextView? = activity?.findViewById(R.id.toolbar_text)
        if (title != null) {
            title.setText("EXPLORE")
        };

        val viewPager = root.findViewById<ViewPager2>(R.id.pager)
        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val adapter =
            activity?.let { ViewPagerAdapter(requireActivity().applicationContext,tabLayout!!.tabCount, it) }
        viewPager!!.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when(position)
            {
                0-> tab.text = "Movies"
                1-> tab.text = "Sports"
                2-> tab.text= "Tech"
                3-> tab.text= "Science"
                4-> tab.text= "Business"
                5-> tab.text= "Movies"
                6-> tab.text="Auto"
                7-> tab.text="Politics"
                8-> tab.text="Health"
            }

        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
              //  Toast.makeText(activity!!.applicationContext, "Tab ${tab?.text} unselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
               // Toast.makeText(activity!!.applicationContext, "Tab ${tab?.text} reselected", Toast.LENGTH_SHORT).show()
            }
        })
        return  root
    }
}