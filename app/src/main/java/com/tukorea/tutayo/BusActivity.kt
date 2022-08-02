package com.tukorea.tutayo
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class BusActivity : AppCompatActivity() {
    lateinit var intertv:TextView
    lateinit var intertv2:TextView
    var updateview: View? = null // above oncreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_activity)


        val df = SimpleDateFormat("HH:mm")
        val list_item = mutableListOf<String>()
        intertv = findViewById(R.id.intertv)
        intertv2 = findViewById(R.id.intertv2)


        val list_item3 = mutableListOf<Int>()
        var item1 = "9:00"


        list_item.add(item1)
        list_item.add("9:20")
        list_item.add("9:50")

        list_item.add("10:10")
        list_item.add("10:25")
        list_item.add("10:45")

        list_item.add("11:00")
        list_item.add("11:20")
        list_item.add("11:40")

        list_item.add("12:00")
        list_item.add("12:22")
        list_item.add("12:45")

        list_item.add("13:02")
        list_item.add("13:15")
        list_item.add("13:25")
        list_item.add("13:45")

        list_item.add("14:07")
        list_item.add("14:30")
        list_item.add("14:50")

        list_item.add("15:10")
        list_item.add("15:20")
        list_item.add("15:30")
        list_item.add("15:45")

        list_item.add("16:05")
        list_item.add("16:20")
        list_item.add("16:30")
        list_item.add("16:45")

        list_item.add("17:05")
        list_item.add("17:25")
        list_item.add("17:45")

        list_item.add("18:05")
        list_item.add("18:25")
        list_item.add("18:45")

        list_item.add("19:05")
        list_item.add("19:25")
        list_item.add("19:45")

        list_item.add("20:10")



        list_item3.add(9*60)
        list_item3.add(9*60+20)
        list_item3.add( 9*60+50 )

        list_item3.add( 10*60+10 )
        list_item3.add( 10*60+25 )
        list_item3.add( 10*60+45 )

        list_item3.add( 11*60+0 )
        list_item3.add( 11*60+20 )
        list_item3.add( 11*60+40 )

        list_item3.add( 12*60+0 )
        list_item3.add( 12*60+22 )
        list_item3.add( 12*60+45 )

        list_item3.add( 13*60+2 )
        list_item3.add( 13*60+15 )
        list_item3.add( 13*60+25 )
        list_item3.add( 13*60+45 )

        list_item3.add( 14*60+7 )
        list_item3.add( 14*60+30 )
        list_item3.add( 14*60+50 )

        list_item3.add( 15*60+10 )
        list_item3.add( 15*60+20 )
        list_item3.add( 15*60+30 )
        list_item3.add( 15*60+45 )

        list_item3.add( 16*60+5 )
        list_item3.add( 16*60+20 )
        list_item3.add( 16*60+30 )
        list_item3.add( 16*60+45 )

        list_item3.add( 17*60+5 )
        list_item3.add( 17*60+25 )
        list_item3.add( 17*60+45 )

        list_item3.add( 18*60+5 )
        list_item3.add( 18*60+25 )
        list_item3.add( 18*60+45 )

        list_item3.add( 19*60+5 )
        list_item3.add( 19*60+25 )
        list_item3.add( 19*60+45 )

        list_item3.add( 20*60+10 )

        val list_item2 = mutableListOf<String>()
        val list_item4 = mutableListOf<Int>()

        list_item2.add("8:41")
        list_item2.add("8:59")

        list_item2.add("9:5")
        list_item2.add("9:15")
        list_item2.add("9:20")
        list_item2.add("9:35")
        list_item2.add("9:50")

        list_item2.add("10:05")
        list_item2.add("10:20")
        list_item2.add("10:35")
        list_item2.add("10:55")

        list_item2.add("11:10")
        list_item2.add("11:30")
        list_item2.add("11:50")

        list_item2.add("12:10")
        list_item2.add("12:32")
        list_item2.add("12:55")

        list_item2.add("13:12")
        list_item2.add("13:25")
        list_item2.add("13:35")
        list_item2.add("13:55")

        list_item2.add("14:17")
        list_item2.add("14:40")

        list_item2.add("15:00")
        list_item2.add("15:20")
        list_item2.add("15:30")
        list_item2.add("15:40")
        list_item2.add("15:55")

        list_item2.add("16:15")
        list_item2.add("16:30")
        list_item2.add("16:40")
        list_item2.add("16:55")
        list_item4.add(8*60+41)
        list_item4.add( 8*60+59 )

        list_item4.add( 9*60+5 )
        list_item4.add( 9*60+15 )
        list_item4.add( 9*60+20 )
        list_item4.add( 9*60+35 )
        list_item4.add( 9*60+50 )

        list_item4.add( 10*60+5 )
        list_item4.add( 10*60+20 )
        list_item4.add( 10*60+35 )
        list_item4.add( 10*60+55 )

        list_item4.add( 11*60+10 )
        list_item4.add( 11*60+30 )
        list_item4.add( 11*60+50 )

        list_item4.add( 12*60+10 )
        list_item4.add( 12*60+32 )
        list_item4.add( 12*60+55 )

        list_item4.add( 13*60+12 )
        list_item4.add( 13*60+25 )
        list_item4.add( 13*60+35 )
        list_item4.add( 13*60+55 )

        list_item4.add( 14*60+17 )
        list_item4.add( 14*60+40 )

        list_item4.add( 15*60+0 )
        list_item4.add( 15*60+20 )
        list_item4.add( 15*60+30 )
        list_item4.add( 15*60+40 )
        list_item4.add( 15*60+55 )

        list_item4.add( 16*60+15 )
        list_item4.add( 16*60+30 )
        list_item4.add( 16*60+40 )
        list_item4.add( 16*60+55 )

        var listview = findViewById<ListView>(R.id.mainListview)


        var listAdapter = ListViewAdapter(list_item)
        listview.adapter = listAdapter


        var listview2 = findViewById<ListView>(R.id.mainListview2)
        var listAdapter2 = ListViewAdapter2(list_item2)
        listview2.adapter = listAdapter2


        var current = System.currentTimeMillis() //long타입의 초
        Log.d("다미",df.format(current).toString())
        var curr = df.format(current).toString()
        var calToday: Calendar = Calendar.getInstance()
        var calDDay: Calendar = Calendar.getInstance()

        var curr_arr = curr.split(":")
        var curr_hour = curr_arr[0] //현재 시 string
        var curr_min = curr_arr[1]  //현재 분 string


        //현재시간 -->-->millionseconds로 변화
//calToday.set(Calendar.HOUR_OF_DAY,Calendar.HOUR_OF_DAY.toInt())
//calToday.set(Calendar.MINUTE,Calendar.MINUTE.toInt())
        var caltoday = curr_hour.toInt()*60 +curr_min.toInt() //시간과 분을 int로 바꿔서 분으로 합치기 int형 분
        Log.d("지금",caltoday.toString())
        Log.d("지금2",list_item3[1].toString())
//버스시간-현재시간 0보다 크거나 같을때 그값을 var neartime으로 지정
        var nearschooltime:Int?=0
        val schoolinter:String
        var schooltime:Int=0
        var jeongwangtime:Int=0
        for(i in list_item3.indices){
            if(caltoday<=list_item3[i]){
                schooltime=list_item3[i]
                intertv.text = convertSecondsToHMmSs(schooltime)


//listview 와 listview3 비교해서 같은거 있을때 listview에 색칠
                for(j in 0..list_item.size){
                    if(list_item[j]==convertSecondsToHMmSs(schooltime).toString()){
                        Log.d("확",list_item[j])
                        //현재 listview의 위치와 글자는 찾았음
                        Log.d("확1",j.toString())
                        //j번째의 listview의 textcolor를 바꾸고 싶음
                        break
                    }
                }

                break
            }

        }

        for(i in list_item4.indices){
            if(caltoday<=list_item4[i]){
                jeongwangtime=list_item4[i]
                intertv2.text =convertSecondsToHMmSs(jeongwangtime-caltoday).toString()
                Log.d("ㅈㅈㅈㅈ",convertSecondsToHMmSs(jeongwangtime-caltoday).toString())
                break

            }
        }




    }



    fun convertSecondsToHMmSs(seconds: Int): String? {

        val m = seconds % 60
        val h = seconds/60
        return String.format("%d:%02d", h, m)
    }

}
