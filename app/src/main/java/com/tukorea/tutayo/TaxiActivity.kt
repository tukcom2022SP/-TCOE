package com.tukorea.tutayo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.taxi_activity.*
import kotlinx.android.synthetic.main.taxi_fragment_add.*

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

        val intent = getIntent()
        //if(intent.extras != null)
        var userId = intent.getLongExtra("user_id", 0)
        var gender = intent.getStringExtra("user_gender")
        Log.i("TAG","main->taxi intent - user_id: ${userId}, user_gender: ${gender}")

        fragmentManager = supportFragmentManager
        JFragment = JeongwangFragment()
        OFragment = OidoFragment()
        NewFragment = NewTaxiFragment()
        viewPagerFragment = ViewPagerFragment()

        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, viewPagerFragment).commitAllowingStateLoss()

        //새 글 프래그먼트로 사용자 정보 전달
        var bundle = Bundle()

        if(userId != null && gender != null) {
            bundle.putLong("user_id",userId)
            bundle.putString("user_gender",gender)
            NewFragment.arguments = bundle
            JFragment.arguments = bundle
            OFragment.arguments = bundle
            Log.i("TAG","taxiActivity -> NewTaxiFragment bundle - putLong: ${userId}, bundle putString ${gender}")
        }
        else {
            Log.i("TAG", "bundle.putExtra failed")
        }

        //새 글 작성 버튼 클릭시 새 글 프래그먼트로 넘어감
        writeNew.setOnClickListener {
            writeNew.visibility = View.INVISIBLE
            toNewFragment()
        }


    }

    //새 글 작성 프래그먼트 전환
    fun toNewFragment() {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, NewFragment).commitAllowingStateLoss()

        Log.i("TAG","NewTaxiFragment")
    }

    //정왕 프래그먼트 전환
    fun toJFragment() {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, viewPagerFragment).commitAllowingStateLoss()
        writeNew.visibility = View.VISIBLE
        Log.i("TAG","JFragment")
    }

    //오이도 프래그먼트 전환
    fun toOFragment() {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, OFragment).commitAllowingStateLoss()
        Log.i("TAG","OFragment")
    }
}