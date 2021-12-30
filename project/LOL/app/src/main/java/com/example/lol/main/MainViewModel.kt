package com.example.lol.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel(){

    val mainList:MutableLiveData<ArrayList<MainResponse>> = MutableLiveData()
    var choose = 0;

    fun master(){
        val dataList = arrayListOf(
            MainResponse("Master", "1100LP"),
            MainResponse("Master", "1100LP"),
            MainResponse("Master", "1100LP"),
            MainResponse("Master", "1100LP"),
            MainResponse("Master", "1100LP"),
            MainResponse("Master", "1100LP")
            )
        choose = 1
        mainList.value = dataList
    }

    fun gm(){
        val dataList = arrayListOf(
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP"),
            MainResponse("GrandMaster", "1100LP")
            )
        choose = 2
        mainList.value = dataList
    }

    fun chal(){
        val dataList = arrayListOf(
            MainResponse("Challenger", "1100LP"),
            MainResponse("Challenger", "1100LP"),
            MainResponse("Challenger", "1100LP"),
            MainResponse("Challenger", "1100LP"),
            MainResponse("Challenger", "1100LP"),
            MainResponse("Challenger", "1100LP")
            )
        choose = 3
        mainList.value = dataList
    }
}