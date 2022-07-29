package com.tukorea.tutayo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class TaxiActivity : AppCompatActivity() {
    private lateinit var fragmentManager : FragmentManager
    private lateinit var JFragment : Fragment
    private lateinit var OFragment : Fragment
    private lateinit var transaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taxi_activity)


        //커밋용 주석
        fragmentManager = supportFragmentManager
        JFragment = JeongwangFragment()
        OFragment = OidoFragment()

        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, JFragment).commitAllowingStateLoss()






    }
}