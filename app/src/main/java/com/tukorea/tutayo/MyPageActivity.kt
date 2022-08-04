package com.tukorea.tutayo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.mypage_activity.*
import kotlinx.android.synthetic.main.taxi_activity.*

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)

        UserApiClient.instance.me { user, error ->
            id.text = "${user?.id}"
            name.text = "${user?.kakaoAccount?.profile?.nickname}"
            email.text = "${user?.kakaoAccount?.email}"
            gender.text = "${user?.kakaoAccount?.gender}"


            //comment
            /* 확인 결과, 이메일과 성별란은 null이 뜨는데,
            이는 오류가 아니라 카카오 로그인 동의 항목 설정으로 인해 뜨는 문제임
            (이메일과 성별은 필수 동의를 하려면 검수가 필요함, 틀린 코드가 아님)
            */

        }
    }
}