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
    lateinit var OkBtn:Button
    lateinit var ResetBtn:Button
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

        OkBtn = findViewById<Button>(R.id.btnOk)
        ResetBtn = findViewById(R.id.btnReset)
        tx1 = findViewById(R.id.tx1)
        var hour =Integer.parseInt(timePicker.hour.toString())
        var minute= Integer.parseInt(timePicker.minute.toString())
        val dataFormat4 = SimpleDateFormat("HH:mm")

        tx1.text = "현재시각" +dataFormat4.format(current) + " 기준"
//*********************************

        var tx4Cal = Calendar.getInstance()



        //세팅시간
        val datehour = Date(timePicker.hour.toLong())
        val datemin = Date(timePicker.minute.toLong())
        //var ttime :String = dataFormat4.format(date)

        val Cal =Calendar.getInstance()
        var settingtime: Date = dataFormat4.parse("15:00")//지정시간넣기

        //버스시간 넣기
        val cal1 = Calendar.getInstance()
        val bus1: Date = dataFormat4.parse("13:05")

        val cal2 = Calendar.getInstance()
        val bus2: Date = dataFormat4.parse("13:05")
        //기준시간과 가장 가까운 시간 찾기

        //시간 차이 출력하기
        Cal.time =settingtime //지정시간

        cal1.time =bus1 //버스시간
        cal2.time =bus2
       // Cal.add(Cal.time.hours, -cal1.time.hours)
        //Cal.add(Cal.time.minutes, -cal1.time.minutes)
        Cal.add(Calendar.HOUR_OF_DAY, -cal1.time.hours)
        Cal.add(Calendar.MINUTE, -cal1.time.minutes)

        tx4Cal.add(Calendar.HOUR_OF_DAY, -cal1.time.hours)
        tx4Cal.add(Calendar.MINUTE, -cal1.time.minutes)

        //*************************************
//OK버튼 눌렀을때 지정 시간 text가 뜨고 bus시간과 시간차가 보인다.
        OkBtn.setOnClickListener {
            tx1.text = timePicker.hour.toString() + " : " + timePicker.minute.toString() + " 기준"
            // 시간간격 출력
            text.text = dataFormat4.format(Cal.time)}

        ResetBtn.setOnClickListener {
            tx1.text =  "현재시각" +dataFormat4.format(current) + " 기준"
            text.text = dataFormat4.format(tx4Cal.time)
        }

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

    }}


