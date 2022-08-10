package com.tukorea.tutayo

import android.graphics.Color
import android.icu.util.Calendar
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

import java.text.SimpleDateFormat
import java.util.*

class RVAdapter (val items : MutableList<String>) : RecyclerView.Adapter<RVAdapter.ViewHolder>(){
    private val now: Long = System.currentTimeMillis()
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

            val item3 = mutableListOf<Long>()




            item3.add(getSpecificTime(9, 0))
            item3.add(getSpecificTime(9, 20))
            item3.add(getSpecificTime(9, 50))

            item3.add(getSpecificTime(10, 10))
            item3.add(getSpecificTime(10, 25))
            item3.add(getSpecificTime(10, 45))

            item3.add(getSpecificTime(11, 0))
            item3.add(getSpecificTime(11, 20))
            item3.add(getSpecificTime(11, 40)) //10

            item3.add(getSpecificTime(12, 0))
            item3.add(getSpecificTime(12, 22))
            item3.add(getSpecificTime(12, 45))

            item3.add(getSpecificTime(13, 2))
            item3.add(getSpecificTime(13, 15))
            item3.add(getSpecificTime(13, 25))
            item3.add(getSpecificTime(13, 45))

            item3.add(getSpecificTime(14, 7))
            item3.add(getSpecificTime(14, 30))
            item3.add(getSpecificTime(14, 50))//20

            item3.add(getSpecificTime(15, 10))
            item3.add(getSpecificTime(15, 20))
            item3.add(getSpecificTime(15, 30))
            item3.add(getSpecificTime(15, 45))

            item3.add(getSpecificTime(16, 5))
            item3.add(getSpecificTime(16, 20))
            item3.add(getSpecificTime(16, 30))
            item3.add(getSpecificTime(16, 45))

            item3.add(getSpecificTime(17, 5))
            item3.add(getSpecificTime(17, 25))
            item3.add(getSpecificTime(17, 45))//31

            item3.add(getSpecificTime(18, 5))
            item3.add(getSpecificTime(18, 25))
            item3.add(getSpecificTime(18, 45))

            item3.add(getSpecificTime(19, 5))
            item3.add(getSpecificTime(19, 25))
            item3.add(getSpecificTime(19, 45))

            item3.add(getSpecificTime(20, 10)) //37


            val time = itemView.findViewById<TextView>(R.id.RcyviewItem)

            var currTime = getCurrentTime()

            if(adapterPosition == 0) {
                if(currTime <= item3[adapterPosition]) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            } else if (adapterPosition < 36) {
                if(item3[adapterPosition -1] <= currTime && item3[adapterPosition] > currTime) {
                    time.setTextColor(Color.BLACK)
                    time.setBackgroundColor(Color.LTGRAY)
                    time.typeface.isBold
                    time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21F);
                }
            } else {
                if (currTime > getSpecificTime(19,45) &&currTime<getSpecificTime(20,10)) {
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



