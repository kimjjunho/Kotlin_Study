package com.example.coffee

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dataList
import total_minus
import total_plus

class MainAdapter(private val mainList: ArrayList<MainData>):RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main,parent,false)
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
    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val view : View = itemView
        val total : TextView = view.findViewById(R.id.total)
        val plus : EditText = view.findViewById(R.id.plus)
        val minus : EditText = view.findViewById(R.id.minus)
        val date : EditText = view.findViewById(R.id.date)
        val itemBtn : Button = view.findViewById(R.id.item_btn)
        val hide : Button = view.findViewById(R.id.hide)

        fun bind(list:MainData){
            total.text = list.total
            plus.setText(list.plus)
            minus.setText(list.minus)
            date.setText(list.date)
            if(list.click){
                itemBtn.setBackgroundColor(Color.BLUE)
                plus.isEnabled = false
            }else{
                itemBtn.setBackgroundColor(Color.RED)
                plus.isEnabled = true
            }
            date.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    dataList[position] = MainData(date.text.toString(),plus.text.toString(), minus.text.toString(), total.text.toString(),dataList[position].click)
                }

                override fun afterTextChanged(p0: Editable?) {}

            })
            plus.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(plus.text.toString()!=""){
                        if(minus.text.toString()!="")
                            total.text = (plus.text.toString().toInt() - minus.text.toString().toInt()).toString()
                        else
                            total.text = plus.text.toString()
                    }
                    else{
                        total.text = minus.text.toString()
                    }
                    dataList[position] = MainData(date.text.toString(),plus.text.toString(), minus.text.toString(), total.text.toString(),dataList[position].click)
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
            minus.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(minus.text.toString()!="") {
                        if (plus.text.toString() != "")
                            total.text = (plus.text.toString().toInt() - minus.text.toString().toInt()).toString()
                        else
                            total.text = "-" + minus.text.toString()
                    }
                    else{
                        total.text = plus.text.toString()
                    }
                    dataList[position] = MainData(date.text.toString(),plus.text.toString(), minus.text.toString(), total.text.toString(),dataList[position].click)
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
            itemBtn.setOnClickListener {
                if(dataList[position].click){
                    itemBtn.setBackgroundColor(Color.RED)
                    plus.isEnabled = true
                    minus.isEnabled = true
                    if(minus.text.toString()!=""){
                        total_minus -= minus.text.toString().toInt()
                    }
                    if(plus.text.toString()!=""){
                        total_plus -= plus.text.toString().toInt()
                    }
                    dataList[position] = MainData(date.text.toString(),plus.text.toString(), minus.text.toString(), total.text.toString(),false)
                }else{
                    itemBtn.setBackgroundColor(Color.BLUE)
                    plus.isEnabled = false
                    minus.isEnabled = false
                    if(minus.text.toString()!=""){
                        total_minus += minus.text.toString().toInt()
                    }
                    if(plus.text.toString()!=""){
                        total_plus += plus.text.toString().toInt()
                    }
                    dataList[position] = MainData(date.text.toString(),plus.text.toString(), minus.text.toString(), total.text.toString(),true)
                }

            }
        }
    }
}