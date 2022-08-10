package com.tukorea.tutayo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.mypage_activity.*
import kotlinx.android.synthetic.main.taxi_activity.*

class MyPageActivity : AppCompatActivity() {
    lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)
            image = findViewById(R.id.img)

        UserApiClient.instance.me { user, error ->
            id.text = "${user?.id}"
            name.text = "${user?.kakaoAccount?.profile?.nickname}"
            email.text = "${user?.kakaoAccount?.email}"
            gender.text = "${user?.kakaoAccount?.gender}"
            if(gender.text=="FEMALE"){
                image.setImageResource(R.drawable.aunt)
            }
            /*else if(gender.text =="MALE"){
                image.setImageResource(R.drawable.uncle)

            }*/
        }

    }

}