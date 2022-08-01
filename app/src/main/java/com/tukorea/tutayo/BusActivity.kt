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
    lateinit var linear: LinearLayout
    lateinit var settingTime: Switch
    lateinit var busschedule: Switch
    lateinit var scheduleimg: ImageView
    lateinit var timePicker: TimePicker
    lateinit var OkBtn: Button
    lateinit var ResetBtn: Button
    lateinit var tx1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_activity)

    }
}

