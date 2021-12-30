package com.example.lol.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.main.MainResponse
import com.example.lol.R

class GMAdapter(private val mainList:ArrayList<MainResponse>):RecyclerView.Adapter<GMAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tear,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
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

        fun bind(list: MainResponse){
            image.setImageResource(R.drawable.gm)
            nick.text = list.nick
           // score.setTextColor(ContextCompat.getColor(MainActivity(), R.color.red))
            score_gm.text = list.score
            score_gm.visibility = View.VISIBLE
        }
    }
}