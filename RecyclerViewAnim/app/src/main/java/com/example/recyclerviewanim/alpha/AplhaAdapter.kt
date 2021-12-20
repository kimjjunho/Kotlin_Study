package com.example.recyclerviewanim.alpha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R

class AplhaAdapter(val list: ArrayList<Data>):RecyclerView.Adapter<AplhaAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: AplhaAdapter.CustomViewHolder, position: Int) {
        holder.item_num.text = list[position].num.toString()
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CustomViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val item_num : TextView = itemView.findViewById(R.id.item_num)
    }

}