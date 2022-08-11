package com.tukorea.tutayo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MYMYActivity : AppCompatActivity() {

    lateinit var img1: LinearLayout
    lateinit var img2: LinearLayout
    lateinit var img3: LinearLayout
    lateinit var img4: LinearLayout
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mymyactivity)

        img1 = findViewById(R.id.female1)
        img2 = findViewById(R.id.female2)
        img3 = findViewById(R.id.female3)
        img4 = findViewById(R.id.female4)
        btn = findViewById(R.id.detail_cancelBtn)

        img1.setOnClickListener{
            Toast.makeText(this ,"합승이 허용되었습니다", Toast.LENGTH_SHORT).show()
            img1.visibility = View.GONE
            img3.visibility = View.VISIBLE
        }
        img2.setOnClickListener{
            Toast.makeText(this, "합승이 허용되었습니다", Toast.LENGTH_SHORT).show()
            img2.visibility = View.GONE
            img4.visibility = View.VISIBLE
        }
        btn.setOnClickListener {
            onBackPressed()
        }



    }
}