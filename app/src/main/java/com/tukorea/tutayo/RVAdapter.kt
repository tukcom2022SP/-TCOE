package com.tukorea.tutayo

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.taxi_fragment_new.*
import java.text.SimpleDateFormat

class RVAdapter (val items : MutableList<String>) : RecyclerView.Adapter<RVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.realbus_item1, parent, false)

        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        if(itemClick != null) {
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item : String) {

            val item3 = mutableListOf<Int>()
            item3.add(9 * 60)
            item3.add(9 * 60 + 20)
            item3.add(9 * 60 + 50)

            item3.add(10 * 60 + 10)
            item3.add(10 * 60 + 25)
            item3.add(10 * 60 + 45)

            item3.add(11 * 60 + 0)
            item3.add(11 * 60 + 20)
            item3.add(11 * 60 + 40)

            item3.add(12 * 60 + 0)
            item3.add(12 * 60 + 22)
            item3.add(12 * 60 + 45)

            item3.add(13 * 60 + 2)
            item3.add(13 * 60 + 15)
            item3.add(13 * 60 + 25)
            item3.add(13 * 60 + 45)

            item3.add(14 * 60 + 7)
            item3.add(14 * 60 + 30)
            item3.add(14 * 60 + 50)

            item3.add(15 * 60 + 10)
            item3.add(15 * 60 + 20)
            item3.add(15 * 60 + 30)
            item3.add(15 * 60 + 45)

            item3.add(16 * 60 + 5)
            item3.add(16 * 60 + 20)
            item3.add(16 * 60 + 30)
            item3.add(16 * 60 + 45)

            item3.add(17 * 60 + 5)
            item3.add(17 * 60 + 25)
            item3.add(17 * 60 + 45)

            item3.add(18 * 60 + 5)
            item3.add(18 * 60 + 25)
            item3.add(18 * 60 + 45)

            item3.add(19 * 60 + 5)
            item3.add(19 * 60 + 25)
            item3.add(19 * 60 + 45)

            item3.add(20 * 60 + 10)

            val time = itemView.findViewById<TextView>(R.id.RcyviewItem)



            val df = SimpleDateFormat("HH:mm")
            var current = System.currentTimeMillis() //long타입의 초

            var curr = df.format(current).toString()
            var curr_arr = curr.split(":")
            var curr_hour = curr_arr[0] //현재 시 string
            var curr_min = curr_arr[1]  //현재 분 string
            var caltoday = curr_hour.toInt() * 60 + curr_min.toInt()

            var curr_arr2 = items[position].split(":")
            var curr_hour2 = curr_arr2[0] //현재 시 string
            var curr_min2 = curr_arr2[1]  //현재 분 string
            var caltoday2 = curr_hour2.toInt() * 60 + curr_min2.toInt()


            if (position < 36) {

                val mireInter = item3[position + 1] - item3[position]
//현재와 지금 위치 격차 18분   지금이랑 이후 격차는 10
                if (caltoday <= caltoday2 && caltoday2 < caltoday + mireInter) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);




                }
            }
            else {
                if (caltoday > 19 * 60 + 45) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            }

            time.text = item

        }
    }


}
