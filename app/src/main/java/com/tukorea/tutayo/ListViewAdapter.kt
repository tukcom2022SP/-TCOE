package com.tukorea.tutayo

import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

import java.text.SimpleDateFormat
class ListViewAdapter(val List: MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var list_item3 = mutableListOf<Int>()
        list_item3.add(9 * 60)
        list_item3.add(9 * 60 + 20)
        list_item3.add(9 * 60 + 50)

        list_item3.add(10 * 60 + 10)
        list_item3.add(10 * 60 + 25)
        list_item3.add(10 * 60 + 45)

        list_item3.add(11 * 60 + 0)
        list_item3.add(11 * 60 + 20)
        list_item3.add(11 * 60 + 40)

        list_item3.add(12 * 60 + 0)
        list_item3.add(12 * 60 + 22)
        list_item3.add(12 * 60 + 45)

        list_item3.add(13 * 60 + 2)
        list_item3.add(13 * 60 + 15)
        list_item3.add(13 * 60 + 25)
        list_item3.add(13 * 60 + 45)

        list_item3.add(14 * 60 + 7)
        list_item3.add(14 * 60 + 30)
        list_item3.add(14 * 60 + 50)

        list_item3.add(15 * 60 + 10)
        list_item3.add(15 * 60 + 20)
        list_item3.add(15 * 60 + 30)
        list_item3.add(15 * 60 + 45)

        list_item3.add(16 * 60 + 5)
        list_item3.add(16 * 60 + 20)
        list_item3.add(16 * 60 + 30)
        list_item3.add(16 * 60 + 45)

        list_item3.add(17 * 60 + 5)
        list_item3.add(17 * 60 + 25)
        list_item3.add(17 * 60 + 45)

        list_item3.add(18 * 60 + 5)
        list_item3.add(18 * 60 + 25)
        list_item3.add(18 * 60 + 45)

        list_item3.add(19 * 60 + 5)
        list_item3.add(19 * 60 + 25)
        list_item3.add(19 * 60 + 45)

        list_item3.add(20 * 60 + 10)


        var converView = convertView

        if (converView == null) {
            converView =
                LayoutInflater.from(parent?.context).inflate(R.layout.bus_timeitem1, parent, false)
        }


        val time = converView!!.findViewById<TextView>(R.id.listviewItem)
        time.text = List[position]

        //= converView2!!.findViewById<TextView>(R.id.intertv)

        val df = SimpleDateFormat("HH:mm")
        var current = System.currentTimeMillis() //long타입의 초

        var curr = df.format(current).toString()
        var curr_arr = curr.split(":")
        var curr_hour = curr_arr[0] //현재 시 string
        var curr_min = curr_arr[1]  //현재 분 string
        var caltoday = curr_hour.toInt() * 60 + curr_min.toInt()

        var curr_arr2 = List[position].split(":")
        var curr_hour2 = curr_arr2[0] //현재 시 string
        var curr_min2 = curr_arr2[1]  //현재 분 string
        var caltoday2 = curr_hour2.toInt() * 60 + curr_min2.toInt()


// i position i==potion때 i를 a i+1을 b  i와 i+1의 분 격차를봐
/*
         for(i in 1..list_item3.size){

            if(i==position){
                val restInter=list_item3[position]-list_item3[position-1]
                if(caltoday <= caltoday2 && ((list_item3[position-1] +restInter-1) >= caltoday2)){
                    time.setTextColor(Color.BLUE)
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23F);
                    return converView!!
            }

            Log.d("ㄱㄷ2", position.toString())
       //만약 둘이 15분 차이... 1분 지남
             return converView!!
         }
        }*/
        if (position < 36) {
            val restInter = list_item3[position] - caltoday
            val restInter2 = list_item3[position + 1] - caltoday
            val mireInter = list_item3[position + 1] - list_item3[position]
//현재와 지금 위치 격차 18분   지금이랑 이후 격차는 10
            if (caltoday <= caltoday2 && caltoday2 < caltoday + mireInter) {
                time.setTextColor(Color.BLUE)
                time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23F);
                return converView!!
            } else {
                return converView!!
            }
        } else {
            if (caltoday > 19 * 60 + 45) {
                time.setTextColor(Color.BLUE)
                time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23F);
                return converView!!
            } else {
                return converView!!
            }
        }

        return converView!!


    }

}


