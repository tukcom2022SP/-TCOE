package com.tukorea.tutayo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.kakao.sdk.common.util.Utility
import kotlinx.android.synthetic.main.main_toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView : NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var menuButton : ImageView
    lateinit var shuttleButton: ImageButton
    lateinit var taxiButton: ImageButton

    //=================== 테스트용, 후에 삭제예정 ==================================
    //나중에 코드 정리할때 위에 import android.wiget.Switch도 잊지 않고 삭제하기!
    lateinit var testSwitch : Switch
    //===========================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_drawer)


        navigationView = findViewById<NavigationView>(R.id.nav_view)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        menuButton = findViewById<ImageView>(R.id.menuBtn)
        shuttleButton = findViewById<ImageButton>(R.id.shuttleBtn)
        taxiButton = findViewById<ImageButton>(R.id.taxiBtn)
        testSwitch = findViewById<Switch>(R.id.test_switch)


        val keyHash = Utility.getKeyHash(this)
        Log.e("Key", "keyHash : ${keyHash}")

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            Toast.makeText(this, "메뉴 버튼 실행 테스트", Toast.LENGTH_SHORT).show()

        }

        navigationView.setNavigationItemSelectedListener(this)

        shuttleButton.setOnClickListener {
            Toast.makeText(this, "셔틀 버튼 실행 테스트", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,BusActivity::class.java)
            startActivity(intent)
        }

        taxiButton.setOnClickListener {
            Toast.makeText(this, "택시 버튼 실행 테스트", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,TaxiActivity::class.java)
            startActivity(intent)
        }


        //==============================================================================
        //switch 버튼을 통해 전환시에 메뉴가 올바르게 바뀌나 테스트
        //20220731 테스트 결과 : 성공

        testSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                Toast.makeText(this, "쨘 로그아웃 등장이오", Toast.LENGTH_SHORT).show()
                navigationView.getMenu().findItem(R.id.menu_logout).setVisible(true)
                navigationView.getMenu().findItem(R.id.menu_login).setVisible(false)
            }
            else{
                Toast.makeText(this, "쨘 로그인 등장이오", Toast.LENGTH_SHORT).show()
                navigationView.getMenu().findItem(R.id.menu_logout).setVisible(false)
                navigationView.getMenu().findItem(R.id.menu_login).setVisible(true)
            }
        }

        // ============================================================================
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_login -> Toast.makeText(this, "로그인 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_logout -> {
                //if, 로그인이 되어 있을 때 실행
                Toast.makeText(this, "로그아웃 메뉴 실행", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_mypage -> Toast.makeText(this, "마이페이지 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_shuttle -> {
                Toast.makeText(this, "셔틀버스 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, BusActivity::class.java)
                startActivity(intent)
            }

            R.id.menu_taxi -> {
                //만약 택시 메뉴로 진입했을 때, 로그인이 되어 있지 않다면 로그인이 필요합니다 메뉴로 리턴하고
                //아니면 택시액티비티로 리턴 시키기

                Toast.makeText(this, "택시매칭 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TaxiActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawers()
        return false

    }
}