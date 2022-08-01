package com.tukorea.tutayo

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.taxi_activity.*

class TaxiActivity : AppCompatActivity() {
    private lateinit var fragmentManager : FragmentManager
    private lateinit var JFragment : Fragment
    private lateinit var OFragment : Fragment
    private lateinit var NewFragment : Fragment
    private lateinit var transaction : FragmentTransaction
    private lateinit var viewPagerFragment : ViewPagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taxi_activity)

        fragmentManager = supportFragmentManager
        JFragment = JeongwangFragment()
        OFragment = OidoFragment()
        NewFragment = NewTaxiFragment()
        viewPagerFragment = ViewPagerFragment()

        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, viewPagerFragment).commitAllowingStateLoss()

        writeNew.setOnClickListener {
            //새 글 작성 버튼 클릭시 새 글 프래그먼트로 넘어감
            transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.taxi_fragment_frame, NewFragment).commitAllowingStateLoss()
        }






    }
}