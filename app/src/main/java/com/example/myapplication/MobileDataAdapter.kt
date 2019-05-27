package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.bean.MobileData
import kotlinx.android.synthetic.main.item_view.view.*

class MobileDataAdapter (private val dataList: ArrayList<MobileData>, private val listener: Listener) : RecyclerView.Adapter<MobileDataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_view, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(dataList.get(p1), listener, p1)
    }

    interface Listener{
        fun onItemClick(item: MobileData)
    }

    class ViewHolder(v: View) :RecyclerView.ViewHolder(v){

        fun bind(item: MobileData, listener: Listener, position: Int){
            itemView.item_id.text=item._id
            itemView.item_quarter.text=item.quarter
            itemView.item_volume.text=item.volume_of_mobile_data.toString()
            if(item.decreased)
                itemView.img.visibility=View.VISIBLE
            else
                itemView.img.visibility=View.INVISIBLE

            itemView.img.setOnClickListener{listener.onItemClick(item)}
        }
    }
}