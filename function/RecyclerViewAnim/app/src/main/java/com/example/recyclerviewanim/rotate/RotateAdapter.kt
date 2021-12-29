package com.example.recyclerviewanim.rotate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R
import com.example.recyclerviewanim.alpha.AplhaAdapter

class RotateAdapter (val list: ArrayList<Data>): RecyclerView.Adapter<RotateAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RotateAdapter.CustomViewHolder, position: Int) {
        holder.item_num.text = list[position].num.toString()
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rotate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CustomViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val item_num : TextView = itemView.findViewById(R.id.item_num)
    }

}