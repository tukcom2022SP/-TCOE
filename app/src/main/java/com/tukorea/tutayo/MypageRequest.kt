package com.tukorea.tutayo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MypageRequest : AppCompatActivity() {


    lateinit var img1:LinearLayout
    lateinit var img2: LinearLayout
    lateinit var img3: LinearLayout
    lateinit var img4: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_request)

        img1 = findViewById(R.id.female1)
        img2 = findViewById(R.id.female2)
        img3 = findViewById(R.id.female3)
        img4 = findViewById(R.id.female4)


        img1.setOnClickListener{
            img1.visibility = View.GONE
            img3.visibility = View.VISIBLE
        }
        img2.setOnClickListener{
            img2.visibility = View.GONE
            img4.visibility = View.VISIBLE
        }


    }
    }
