package com.example.calendarkt.model.highlight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.calendarkt.R
import kotlinx.android.synthetic.main.highlight_item.view.*
import retrofit2.http.GET
import kotlin.coroutines.coroutineContext

class HighLightAdapter(private val highList:ArrayList<GetHighLighResponse.GetHighLighData>):RecyclerView.Adapter<HighLightAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighLightAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.highlight_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighLightAdapter.CustomViewHolder, position: Int) {
        val list = highList[position]
        holder.apply {
            bind(list)
        }
    }

    override fun getItemCount(): Int {
        return highList.size
    }

    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var view : View = itemView
        fun bind(list:GetHighLighResponse.GetHighLighData){
            view.item_title.text = list.title
            view.item_content.text = list.content
            Glide.with(view)
                .load(list.url)
                .into(view.item_image)
        }
    }

}