package com.tukorea.tutayo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BusActivity : AppCompatActivity() {
    lateinit var linear:LinearLayout
    lateinit var settingTime: Switch
    lateinit var busschedule: Switch
    lateinit var  scheduleimg:ImageView
    lateinit var timePicker:TimePicker
/*    lateinit var tvHour : TextView
    lateinit var tvMinute : TextView
    lateinit var nowHour : TextView
    lateinit var nowMinute : TextView*/
    lateinit var OkBtn:Button
    lateinit var tx1:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_activity)
        val nowbutton: Button = findViewById(R.id.nowtime)
        // val btn : Button = findViewById(R.id.ttime)
        val text: TextView = findViewById(R.id.nextBus_now)
        var start: Long = 0
        var end: Long = 0

        val current = System.currentTimeMillis()



        linear = findViewById(R.id.linear)
        settingTime = findViewById(R.id.settingTime)
        busschedule = findViewById(R.id.schedule)
        scheduleimg = findViewById(R.id.scheduleImg)
        timePicker = findViewById(R.id.timePicker)
        /*      tvHour = findViewById<TextView>(R.id.tvHour)
        tvMinute = findViewById<TextView>(R.id.tvMinute)*/
        OkBtn = findViewById<Button>(R.id.btnOk)
        tx1 = findViewById(R.id.tx1)

        val dataFormat4 = SimpleDateFormat("HH:mm:ss")
        tx1.text = dataFormat4.format(current)
        /* nowHour.text = timePicker.hour.toString()
        nowMinute.text = timePicker.minute.toString()
*/

        nowbutton.setOnClickListener {
            start = SystemClock.elapsedRealtime()
            //현재시간
            Date(start)
        }
        //임시로 현재시간 출력
        /* btn.setOnClickListener{
            end = SystemClock.elapsedRealtime()
           Date(end)
        }*/
        OkBtn.setOnClickListener {
            tx1.text = timePicker.hour.toString() + " 시 " + timePicker.minute.toString() + "분 기준"
            /*    tvHour.text = timePicker.hour.toString()
        tvMinute.text = timePicker.minute.toString()}*/

            // 시간간격 출력
            text.setText("가장 가까운 버스 간격 : " + (end - start).toString() + "ms")
            settingTime.setOnClickListener {
                if (settingTime.isChecked) {
                    linear.visibility = View.VISIBLE
                } else {
                    linear.visibility = View.GONE
                }

            }
            busschedule.setOnClickListener {
                if (busschedule.isChecked) {
                    scheduleimg.visibility = View.VISIBLE
                } else {
                    scheduleimg.visibility = View.GONE
                }

            }


        }
    }}

