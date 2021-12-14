package com.example.mvvmpractice.bind

import androidx.databinding.ObservableField

class ViewModelD {
    val name = ObservableField("")

    init {
        name.set("lzone")
    }

    fun nameClick() = name.set("Click")
}