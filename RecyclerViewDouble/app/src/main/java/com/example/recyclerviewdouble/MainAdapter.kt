package com.example.recyclerviewdouble

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val mainList:ArrayList<MainData>):RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.CustomViewHolder, position: Int) {
        holder.item_text.text = mainList[position].item_text
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class CustomViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val item_text : TextView = itemView.findViewById(R.id.item_text)
    }
}