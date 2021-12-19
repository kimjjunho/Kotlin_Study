package com.example.mvvmpractice.bind

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ViewModelD : ViewModel(){
    val name = ObservableField("")
    init {
        name.set("lzone")
    }

    fun nameClick() = name.set("Click")
}