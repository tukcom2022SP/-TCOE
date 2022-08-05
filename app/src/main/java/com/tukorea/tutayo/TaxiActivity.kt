package com.tukorea.tutayo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.taxi_activity.*
import kotlinx.android.synthetic.main.taxi_fragment_new.*

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

        var userId = intent.getLongExtra("user_id", 0)
        //var gender = intent.get ...

        fragmentManager = supportFragmentManager
        JFragment = JeongwangFragment()
        OFragment = OidoFragment()
        NewFragment = NewTaxiFragment()
        viewPagerFragment = ViewPagerFragment()

        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, viewPagerFragment).commitAllowingStateLoss()

        //새 글 프래그먼트로 사용자 정보 전달
        var bundle = Bundle()
        bundle.putLong("user_id",0)
        //bundle.put ... 성별 정보
        NewFragment.arguments = bundle

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

        Log.i("TAG","새 글 작성 프래그먼트 호출")
    }

    //정왕 프래그먼트 전환
    fun toJFragment() {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.taxi_fragment_frame, viewPagerFragment).commitAllowingStateLoss()
        writeNew.visibility = View.VISIBLE
        Log.i("TAG","게시글 프래그먼트 호출")
    }

//    //오이도 프래그먼트 전환
//    fun toOFragment() {
//        transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.taxi_fragment_frame, OFragment).commitAllowingStateLoss()
//        Log.i("TAG","오이도 프래그먼트 호출")
//    }

//    override fun onBackPressed() {
//        //현재 게시글 리스트 페이지인 경우 메인 화면으로 이동
//        //현재 새 게시글 작성 페이지인 경우 정왕 프래그먼트로 이동
//    }
}