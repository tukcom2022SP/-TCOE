package com.tukorea.tutayo

import android.graphics.Color
import android.icu.util.Calendar
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class RVAdapter2  (private val items : MutableList<String>) :RecyclerView.Adapter<RVAdapter2.ViewHolder>(){
    private val now: Long = System.currentTimeMillis()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter2.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.realbus_item2, parent, false)

        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }


    var itemClick : ItemClick? = null


    override fun onBindViewHolder(holder: RVAdapter2.ViewHolder, position: Int) {
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
            val item4 = mutableListOf<Long>()
            item4.add(getSpecificTime(8, 41))
            item4.add(getSpecificTime(8, 59))

            item4.add(getSpecificTime(9, 5))
            item4.add(getSpecificTime(9, 15))
            item4.add(getSpecificTime(9, 20))
            item4.add(getSpecificTime(9, 35))
            item4.add(getSpecificTime(9, 50))

            item4.add(getSpecificTime(10, 5))
            item4.add(getSpecificTime(10, 20))
            item4.add(getSpecificTime(10, 35))
            item4.add(getSpecificTime(10, 55))

            item4.add(getSpecificTime(11, 10))
            item4.add(getSpecificTime(11, 30))
            item4.add(getSpecificTime(11, 50))

            item4.add(getSpecificTime(12, 10))
            item4.add(getSpecificTime(12, 32))
            item4.add(getSpecificTime(12, 55))

            item4.add(getSpecificTime(13, 12))
            item4.add(getSpecificTime(13, 25))
            item4.add(getSpecificTime(13, 35))
            item4.add(getSpecificTime(13, 55))

            item4.add(getSpecificTime(14, 17))
            item4.add(getSpecificTime(14, 40))

            item4.add(getSpecificTime(15, 0))
            item4.add(getSpecificTime(15, 20))
            item4.add(getSpecificTime(15, 30))
            item4.add(getSpecificTime(15, 40))
            item4.add(getSpecificTime(15, 55)) //28

            item4.add(getSpecificTime(16, 15))
            item4.add(getSpecificTime(16, 30))
            item4.add(getSpecificTime(16, 40))
            item4.add(getSpecificTime(16, 55))  //32

            val time = itemView.findViewById<TextView>(R.id.RcyviewItem2)

            var currTime = getCurrentTime()

            if(adapterPosition == 0) {
                if(currTime <= item4[adapterPosition]) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            } else if (adapterPosition < 31) {
                if(item4[adapterPosition -1] <= currTime && item4[adapterPosition] > currTime) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            } else {
                if (currTime > getSpecificTime(19, 45)) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            }

            time.text = item

        }
    }

    private fun getSpecificTime(hour:Int, minute:Int) : Long {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)

        val simpleDate = SimpleDateFormat("yyyy;MM;dd")
        val timeVal = simpleDate.format(date)
        var timeArr = timeVal.split(";")

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(timeArr[0].toInt(), timeArr[1].toInt() -1, timeArr[2].toInt(), hour, minute, 0)

        return calendar.timeInMillis
    }

    private fun getCurrentTime() : Long {
        val date = Date(now)

        val simpleDate = SimpleDateFormat("yyyy;MM;dd;HH;mm;ss")
        val timeVal = simpleDate.format(date)
        var timeArr = timeVal.split(";")
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(timeArr[0].toInt(), timeArr[1].toInt() -1, timeArr[2].toInt(), timeArr[3].toInt(), timeArr[4].toInt(), 0)
        return calendar.timeInMillis
    }

}