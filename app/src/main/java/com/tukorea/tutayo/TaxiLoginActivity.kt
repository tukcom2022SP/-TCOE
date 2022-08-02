package com.tukorea.tutayo

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class TaxiLoginActivity : AppCompatActivity() {
    lateinit var kakao : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taxi_needlogin)

        kakao = findViewById<ImageView>(R.id.kakao_login_button)


        KakaoSdk.init(this, this.getString(R.string.kakao_app_key))
        kakao.setOnClickListener {
            kakaoLogin()
        }
    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨

        val callback:(OAuthToken?, Throwable?) -> Unit = {token, error ->
            if(error != null){
                Toast.makeText(this, "Test) 로그인 실패 : ${error}", Toast.LENGTH_SHORT).show()
            } else if(token != null){
                //최종적으로 카카오 로그인 및 유저 정보 가져온 결과
                UserApiClient.instance.me { user, error ->
                    Toast.makeText(this, "Test) 로그인 성공 : ${user}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) {token, error ->
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
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }


}