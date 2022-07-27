package com.tukorea.tutayo

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(manager: FragmentManager) :  FragmentPagerAdapter(manager) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }

    override fun getPageTitle(position: Int): CharSequence = titleList[position]

    fun addFragment(fragment: Fragment, title: String)
    {
        fragmentList.add(fragment)
        titleList.add(title)
    }


}