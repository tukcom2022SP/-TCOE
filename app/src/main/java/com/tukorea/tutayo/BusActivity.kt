package com.tukorea.tutayo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.time.LocalDate
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
    val Cal =Calendar.getInstance()
    val dataFormat4 = SimpleDateFormat("HH:mm")
    val dataFormat = DateTimeFormatter.ofPattern("HH:mm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_activity)


        var text: TextView = findViewById(R.id.nextBus_now)
        var current = System.currentTimeMillis()

        val nowbutton: Button = findViewById(R.id.nowtime)
        // val btn : Button = findViewById(R.id.ttime)
        var start: Long = 0
        var end: Long = 0

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


        tx1.text = "현재시각" +dataFormat4.format(current) + " 기준"


        var tx4Cal = Calendar.getInstance()


        //세팅시간
        val datehour = Date(timePicker.hour.toLong())
        val datemin = Date(timePicker.minute.toLong())
        //var ttime :String = dataFormat4.format(date)


      //  var settingtime: Date = dataFormat4.parse("$datehour:$datemin")//지정시간넣기
        var settingtime: Date = dataFormat4.parse("13:00")//지정시간넣기

        //버스시간 넣기(정왕역 출발)
        val cal1 = Calendar.getInstance()
        val bus_1: Date = dataFormat4.parse("8:41")
        val bus_2: Date = dataFormat4.parse("8:59")

       /* val bus_3: Date = dataFormat4.parse("9:5")
        val bus_4: Date = dataFormat4.parse("9:15")
        val bus_5: Date = dataFormat4.parse("9:20")
        val bus_6: Date = dataFormat4.parse("9:35")
        val bus_7: Date = dataFormat4.parse("9:50")

        val bus_8: Date = dataFormat4.parse("10:5")
        val bus_9: Date = dataFormat4.parse("10:20")
        val bus_10: Date = dataFormat4.parse("10:35")
        val bus_11: Date = dataFormat4.parse("10:55")

        val bus_12: Date = dataFormat4.parse("11:10")
        val bus_13: Date = dataFormat4.parse("11:30")
        val bus_14: Date = dataFormat4.parse("11:50")*/
var busdate = listOf(subCurrentTime(Cal,bus_1))
var result = busdate.sortedByDescending {
    LocalDate.parse(it,dataFormat)
}

        //버스시간 넣기(학교 출발)

        //기준시간과 가장 가까운 시간 찾기

        //시간 차이 출력하기
        Cal.time =settingtime //지정시간
        cal1.time =bus_1 //버스시간 **최소차이의 버스대입

       // Cal.add(Cal.time.hours, -cal1.time.hours)
        //Cal.add(Cal.time.minutes, -cal1.time.minutes)
        Cal.add(Calendar.HOUR_OF_DAY, -cal1.time.hours)
        Cal.add(Calendar.MINUTE, -cal1.time.minutes)

        tx4Cal.add(Calendar.HOUR_OF_DAY, -cal1.time.hours)
        tx4Cal.add(Calendar.MINUTE, -cal1.time.minutes)




/* var array = ArrayList<Int>()

 //array.add(Integer.parseInt(subCurrentTime(Cal,bus_1)))
/* array.add(sCurrentTime(Cal,bus_2).toInt())
 array.add(subCurrentTime(Cal,bus_3).toInt())
 array.add(subCurrentTime(Cal,bus_4).toInt())
 array.add(subCurrentTime(Cal,bus_5).toInt())*/


// array.sort();

// var mini=array.first() //최소
*/




     //*************************************
//OK버튼 눌렀을때 지정 시간 text가 뜨고 bus시간과 시간차가 보인다.
 OkBtn.setOnClickListener {
   //  tx1.text = timePicker.hour.toString() + " : " + timePicker.minute.toString() + " 기준"
     // 시간간격 출력
   //  text.text = dataFormat4.format(Cal.time)
     tx1.text =  "현재시각" +dataFormat4.format(current) + " 기준"
     //text.text = subCurrentTime(Cal,bus_1)
    // text.text= dataFormat4.format(mini)
 }

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

}fun subCurrentTime(Time: Calendar, BusTime:Date): String { //현재 Time에서 BusTime 빼기


 val cal1 =Calendar.getInstance()
 //BusTime= dataFormat4.parse("8:41")


 cal1.time =BusTime

 Cal.add(Calendar.HOUR_OF_DAY, -cal1.time.hours)
 Cal.add(Calendar.MINUTE, -cal1.time.minutes)

 return dataFormat4.format(Cal.time)
}


}


