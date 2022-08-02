package com.tukorea.tutayo
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter (val List : MutableList<String>) : BaseAdapter(){
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

        var converView = convertView

        if (converView == null) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.bus_timeitem1, parent, false)
        }


        val time = converView!!.findViewById<TextView>(R.id.listviewItem)
        time.text = List[position]

        //time.setTextColor(Color.CYAN)
        

        return converView!!



    }

}