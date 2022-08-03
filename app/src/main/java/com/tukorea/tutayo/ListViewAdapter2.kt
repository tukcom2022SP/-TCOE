package com.tukorea.tutayo
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.SimpleDateFormat

class ListViewAdapter2 (val List : MutableList<String>) : BaseAdapter(){
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

        val list_item4 = mutableListOf<Int>()
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

        var converView = convertView

        if (converView == null) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.bus_timeitem2, parent, false)
        }


        val time = converView!!.findViewById<TextView>(R.id.listviewItem2)
        time.text = List[position]

        //= converView2!!.findViewById<TextView>(R.id.intertv)

        val df = SimpleDateFormat("HH:mm")
        var current = System.currentTimeMillis() //long타입의 초

        var curr = df.format(current).toString()
        var curr_arr = curr.split(":")
        var curr_hour = curr_arr[0] //현재 시 string
        var curr_min = curr_arr[1]  //현재 분 string
        var caltoday = curr_hour.toInt()*60 +curr_min.toInt()

        var curr_arr2 = List[position].split(":")
        var curr_hour2 = curr_arr2[0] //현재 시 string
        var curr_min2 = curr_arr2[1]  //현재 분 string
        var caltoday2 = curr_hour2.toInt()*60 +curr_min2.toInt()

        if(caltoday <= caltoday2 && ((caltoday + 9) >= caltoday2)){
            time.setTextColor(Color.BLUE)
            time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23F);

            return converView!!
        }


        return converView!!



    }

}


