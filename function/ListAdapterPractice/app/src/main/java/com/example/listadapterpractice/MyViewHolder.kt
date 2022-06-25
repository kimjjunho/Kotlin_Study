package com.example.listadapterpractice

import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterpractice.databinding.LayoutViewmodelBinding
import com.google.android.material.snackbar.Snackbar

class MyViewHolder(private val binding: LayoutViewmodelBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(data: Monster){
            with(binding){
                tvName.text = "Name: ${data.name}"
                tvRace.text = "Race: ${data.race}"
                tvLevel.text = "Level: ${data.level}"
                tvStatus.text = "Status: ${data.status[0]} / MP: ${data.status[1]} / EXP: ${data.status[2]}"
                tvEncount.text = "Encount: ${data.encount}"

                layoutViewhloder.setOnClickListener {
                    Snackbar.make(it,"Item $layoutPosition touched",Snackbar.LENGTH_SHORT).show()
                }

            }
        }

    fun setAlpha(alpha: Float){
        with(binding){
            tvName.alpha = alpha
            tvRace.alpha = alpha
            tvLevel.alpha = alpha
            tvStatus.alpha = alpha
            tvEncount.alpha = alpha
        }
    }
}