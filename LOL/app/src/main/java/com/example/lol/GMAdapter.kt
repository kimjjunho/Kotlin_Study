package com.example.lol

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class GMAdapter(private val mainList:ArrayList<MainData>):RecyclerView.Adapter<GMAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GMAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tear,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: GMAdapter.CustomViewHolder, position: Int) {
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
        val score_gm : TextView = view.findViewById(R.id.score_gm)

        fun bind(list:MainData){
            image.setImageResource(R.drawable.gm)
            nick.text = list.nick
           // score.setTextColor(ContextCompat.getColor(MainActivity(), R.color.red))
            score_gm.text = list.score
            score_gm.visibility = View.VISIBLE
        }
    }
}