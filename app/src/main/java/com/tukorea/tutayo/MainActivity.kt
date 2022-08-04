package com.tukorea.tutayo

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.main_toolbar.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView : NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var menuButton : ImageView
    lateinit var shuttleButton: ImageButton
    lateinit var taxiButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_drawer)


        navigationView = findViewById<NavigationView>(R.id.nav_view)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        menuButton = findViewById<ImageView>(R.id.menuBtn)
        shuttleButton = findViewById<ImageButton>(R.id.shuttleBtn)
        taxiButton = findViewById<ImageButton>(R.id.taxiBtn)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        KakaoSdk.init(this, this.getString(R.string.kakao_app_key))
        checkLogin() //최초에 로그인 되어 있는지 체크

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)

        }

        navigationView.setNavigationItemSelectedListener(this)

        shuttleButton.setOnClickListener {

            val intent = Intent(this,BusActivity::class.java)
            startActivity(intent)
        }

        taxiButton.setOnClickListener {
            getLoginData()
        }


    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_login -> {
                kakaoLogin()
            }
            R.id.menu_logout -> {
                val dlg = AlertDialog.Builder(this@MainActivity)
                dlg.setMessage("정말 로그아웃 하시겠습니까?")
                dlg.setNegativeButton("취소",null)
                dlg.setPositiveButton("동의"){ dlg , which->
                    kakaoLogout()
                }
                dlg.show()

            }
            R.id.menu_mypage -> Toast.makeText(this, "마이페이지 메뉴 실행 테스트", Toast.LENGTH_SHORT).show()
            R.id.menu_shuttle -> {
                val intent = Intent(this, BusActivity::class.java)
                startActivity(intent)
            }

            R.id.menu_taxi -> {
                //만약 택시 메뉴로 진입했을 때, 로그인이 되어 있지 않다면 로그인이 필요합니다 토스트 메시지 띄움
                //아니면 택시액티비티로 리턴 시키기
                getLoginData()

            }
        }
        drawerLayout.closeDrawers()
        return false

    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨

        val callback:(OAuthToken?, Throwable?) -> Unit = { token, error ->
            if(error != null){
                Toast.makeText(this, "Test) 로그인 실패 : ${error}", Toast.LENGTH_SHORT).show()
            } else if(token != null){
                //최종적으로 카카오 로그인 및 유저 정보 가져온 결과
                UserApiClient.instance.me { user, error ->
                    Toast.makeText(this, "Test) 로그인 성공 : ${user}", Toast.LENGTH_SHORT).show()
                    navigationView.getMenu().findItem(R.id.menu_logout).setVisible(true)
                    navigationView.getMenu().findItem(R.id.menu_login).setVisible(false)
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Toast.makeText(this, "Test) 로그인 실패 : ${error}", Toast.LENGTH_SHORT).show()

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }


                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Toast.makeText(this, "Test) 로그인 성공 : ${token.accessToken}", Toast.LENGTH_SHORT).show()
                    navigationView.getMenu().findItem(R.id.menu_logout).setVisible(true)
                    navigationView.getMenu().findItem(R.id.menu_login).setVisible(false)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun kakaoLogout(){
        //로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                var dlg = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("알림")
                dlg.setMessage("로그아웃에 실패하였습니다. 다시 시도해 주세요.")
                dlg.show()

            }
            else {

                var dlg = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("알림")
                dlg.setMessage("성공적으로 로그아웃 되었습니다!")
                dlg.show()


                navigationView.getMenu().findItem(R.id.menu_logout).setVisible(false)
                navigationView.getMenu().findItem(R.id.menu_login).setVisible(true)
            }
        }
    }

    private fun getLoginData(){
        UserApiClient.instance.me {user, error ->
            if (error != null) {

                var dlg = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("알림")
                dlg.setMessage("로그인 후에 이용 가능합니다.")
                dlg.show()
            }
            else if(user != null){
                val intent = Intent(this, TaxiActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkLogin(){
        UserApiClient.instance.me {user, error ->
            if (error != null) {

            }
            else if(user != null){
                navigationView.getMenu().findItem(R.id.menu_logout).setVisible(true)
                navigationView.getMenu().findItem(R.id.menu_login).setVisible(false)
            }
        }
    }
}