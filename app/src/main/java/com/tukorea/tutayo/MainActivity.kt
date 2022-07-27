package com.tukorea.tutayo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView : NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var menuButton : ImageView
    lateinit var shuttleButton: ImageButton
    lateinit var taxiButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val drawerview : View = View.inflate(this, R.layout.main_drawer, null)

        navigationView = drawerview.findViewById<NavigationView>(R.id.nav_view)
        drawerLayout = drawerview.findViewById<DrawerLayout>(R.id.drawer_layout)
        menuButton = findViewById<ImageView>(R.id.menuBtn)
        shuttleButton = findViewById<ImageButton>(R.id.shuttleBtn)
        taxiButton = findViewById<ImageButton>(R.id.taxiBtn)





        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            Toast.makeText(this, "메뉴 버튼 실행 테스트", Toast.LENGTH_SHORT).show()

        }
        navigationView.setNavigationItemSelectedListener(this)

        shuttleButton.setOnClickListener {
            Toast.makeText(this, "셔틀 버튼 실행 테스트", Toast.LENGTH_SHORT).show()
        }

        taxiButton.setOnClickListener {
            Toast.makeText(this, "택시 버튼 실행 테스트", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_login -> Toast.makeText(this, "로그인 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_mypage -> Toast.makeText(this, "마이페이지 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_shuttle -> Toast.makeText(this, "셔틀버스 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_taxi -> Toast.makeText(this, "택시 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawers()
        return false
    }
}