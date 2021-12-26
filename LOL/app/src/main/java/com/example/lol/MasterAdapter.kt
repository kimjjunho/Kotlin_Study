package com.example.lol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MasterAdapter(private val mainList:ArrayList<MainData>):RecyclerView.Adapter<MasterAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tear,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MasterAdapter.CustomViewHolder, position: Int) {
        val list = mainList[position]
        holder.apply {
            bind(list)
        }
    }

    override fun getItemCount(): Int {
        return mainList.size
    }
    class CustomViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val view : View = itemView
        val image : ImageView = view.findViewById(R.id.image)
        val nick : TextView = view.findViewById(R.id.nick)
        val score : TextView = view.findViewById(R.id.score)

        fun bind(list:MainData){
            image.setImageResource(R.drawable.m)
            nick.text = list.nick
            score.visibility = View.VISIBLE
            score.text = list.score
        }
    }

}