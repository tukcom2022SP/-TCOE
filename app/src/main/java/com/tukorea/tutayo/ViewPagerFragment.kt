package com.tukorea.tutayo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.taxi_fragment_view_pager.*

class ViewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "oncreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.taxi_fragment_view_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //pagerAdapter생성
        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())

        //2개의 프래그먼트 add
        pagerAdapter.addFragment(JeongwangFragment())
        pagerAdapter.addFragment(OidoFragment())

        taxi_viewpager.adapter = pagerAdapter
        taxi_viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position + 1}")
            }
        })

        //탭레이아웃 attach
        TabLayoutMediator(taxi_tablayout, taxi_viewpager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }
}