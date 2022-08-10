package com.tukorea.tutayo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class mypageAdapter1(val requestList : MutableList<TaxiData>) : BaseAdapter() {
    override fun getCount(): Int {
        return requestList.size
    }

    override fun getItem(position: Int): Any {
        return requestList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {

            view = LayoutInflater.from(parent?.context).inflate(R.layout.mypage_item1, parent, false)

        }

        val title = view?.findViewById<TextView>(R.id.RcyviewItem)



        return view!!
    }
}