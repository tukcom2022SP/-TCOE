package com.tukorea.tutayo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import kotlinx.android.synthetic.main.taxi_fragment_new.*
import java.text.SimpleDateFormat

class RealBusActivity : AppCompatActivity() {
    lateinit var intertv: TextView
    lateinit var intertv2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.realbus_activity)

        val df = SimpleDateFormat("HH:mm")
        val item = mutableListOf<String>()
        val item2 = mutableListOf<String>()
        val item3 = mutableListOf<Int>()
        val item4 = mutableListOf<Int>()

        intertv = findViewById(R.id.intertv)
        intertv2 = findViewById(R.id.intertv2)
      

        item.add("9:00")
        item.add("9:20")
        item.add("9:50")

        item.add("10:10")
        item.add("10:25")
        item.add("10:45")

        item.add("11:00")
        item.add("11:20")
        item.add("11:40")

        item.add("12:00")
        item.add("12:22")
        item.add("12:45")

        item.add("13:02")
        item.add("13:15")
        item.add("13:25")
        item.add("13:45")

        item.add("14:07")
        item.add("14:30")
        item.add("14:50")

        item.add("15:10")
        item.add("15:20")
        item.add("15:30")
        item.add("15:45")

        item.add("16:05")
        item.add("16:20")
        item.add("16:30")
        item.add("16:45")

        item.add("17:05")
        item.add("17:25")
        item.add("17:45")

        item.add("18:05")
        item.add("18:25")
        item.add("18:45")

        item.add("19:05")
        item.add("19:25")
        item.add("19:45")

        item.add("20:10")



        item3.add(9*60)
        item3.add(9*60+20)
        item3.add( 9*60+50 )

        item3.add( 10*60+10 )
        item3.add( 10*60+25 )
        item3.add( 10*60+45 )

        item3.add( 11*60+0 )
        item3.add( 11*60+20 )
        item3.add( 11*60+40 )

        item3.add( 12*60+0 )
        item3.add( 12*60+22 )
        item3.add( 12*60+45 )

        item3.add( 13*60+2 )
        item3.add( 13*60+15 )
        item3.add( 13*60+25 )
        item3.add( 13*60+45 )

        item3.add( 14*60+7 )
        item3.add( 14*60+30 )
        item3.add( 14*60+50 )

        item3.add( 15*60+10 )
        item3.add( 15*60+20 )
        item3.add( 15*60+30 )
        item3.add( 15*60+45 )

        item3.add( 16*60+5 )
        item3.add( 16*60+20 )
        item3.add( 16*60+30 )
        item3.add( 16*60+45 )

        item3.add( 17*60+5 )
        item3.add( 17*60+25 )
        item3.add( 17*60+45 )

        item3.add( 18*60+5 )
        item3.add( 18*60+25 )
        item3.add( 18*60+45 )

        item3.add( 19*60+5 )
        item3.add( 19*60+25 )
        item3.add( 19*60+45 )

        item3.add( 20*60+10 )

       

        item2.add("8:41")
        item2.add("8:59")

        item2.add("9:05")
        item2.add("9:15")
        item2.add("9:20")
        item2.add("9:35")
        item2.add("9:50")

        item2.add("10:05")
        item2.add("10:20")
        item2.add("10:35")
        item2.add("10:55")

        item2.add("11:10")
        item2.add("11:30")
        item2.add("11:50")

        item2.add("12:10")
        item2.add("12:32")
        item2.add("12:55")

        item2.add("13:12")
        item2.add("13:25")
        item2.add("13:35")
        item2.add("13:55")

        item2.add("14:17")
        item2.add("14:40")

        item2.add("15:00")
        item2.add("15:20")
        item2.add("15:30")
        item2.add("15:40")
        item2.add("15:55")

        item2.add("16:15")
        item2.add("16:30")
        item2.add("16:40")
        item2.add("16:55")
        item4.add(8*60+41)
        item4.add( 8*60+59 )

        item4.add( 9*60+5 )
        item4.add( 9*60+15 )
        item4.add( 9*60+20 )
        item4.add( 9*60+35 )
        item4.add( 9*60+50 )

        item4.add( 10*60+5 )
        item4.add( 10*60+20 )
        item4.add( 10*60+35 )
        item4.add( 10*60+55 )

        item4.add( 11*60+10 )
        item4.add( 11*60+30 )
        item4.add( 11*60+50 )

        item4.add( 12*60+10 )
        item4.add( 12*60+32 )
        item4.add( 12*60+55 )

        item4.add( 13*60+12 )
        item4.add( 13*60+25 )
        item4.add( 13*60+35 )
        item4.add( 13*60+55 )

        item4.add( 14*60+17 )
        item4.add( 14*60+40 )

        item4.add( 15*60+0 )
        item4.add( 15*60+20 )
        item4.add( 15*60+30 )
        item4.add( 15*60+40 )
        item4.add( 15*60+55 )

        item4.add( 16*60+15 )
        item4.add( 16*60+30 )
        item4.add( 16*60+40 )
        item4.add( 16*60+55 )

        val rvAdapter = RVAdapter(item)
        val rv = findViewById<RecyclerView>(R.id.mainListview)

        val rvAdapter2 = RVAdapter2(item2)
        val rv2 = findViewById<RecyclerView>(R.id.mainListview2)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)



        rv2.adapter = rvAdapter2
        rv2.layoutManager = LinearLayoutManager(this)

       /* rvAdapter2.itemClick = object : RVAdapter2.ItemClick {
            override fun onClick(view: View, position: Int) {
            }

        }*/



        var current = System.currentTimeMillis()
        var curr = df.format(current).toString()

        var curr_arr = curr.split(":")
        var curr_hour = curr_arr[0] //현재 시 string
        var curr_min = curr_arr[1]  //현재 분 string

        var caltoday = curr_hour.toInt()*60 +curr_min.toInt()

        for(i in item3.indices){
            var cato=0
            if(caltoday<=item3[i]){

                cato++
                if(cato==1){
                    var schooltime=item3[i] -caltoday
                    var s_t=schooltime.toString()
                    if(Integer.parseInt(s_t)<1){
                        intertv.text = "잠시후 도착"
                    }
                    else if(Integer.parseInt(s_t)>25){
                        intertv.text = "버스가 없습니다."
                    }

                    else {
                        intertv.text = String.format("$s_t 분 남았습니다")
                    }
                    break
                }

            }}
        for(i in item4.indices){
            var cato=0
            if(caltoday<=item4[i]){
                cato++
                if(cato==1){
                    var schooltime=item4[i] -caltoday
                    var s_t=schooltime.toString()
                    if(Integer.parseInt(s_t)<1){
                        intertv2.text = "잠시후 도착"
                    }
                    else if(Integer.parseInt(s_t)>25){
                        intertv2.text = "버스가 없습니다."
                    }
                    else {
                        intertv2.text = String.format("$s_t 분 남았습니다")
                    }
                    break
                }

            }}




    }
}