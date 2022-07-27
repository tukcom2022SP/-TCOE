package com.tukorea.tutayo

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.taxi_activity.*

class TaxiActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.taxi_activity)

        var adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(JeongwangFragment(), "정왕")
        adapter.addFragment(OidoFragment(), "오이도")
        taxi_viewpager.adapter = adapter
        location_tab.setupWithViewPager(taxi_viewpager)
    }
}