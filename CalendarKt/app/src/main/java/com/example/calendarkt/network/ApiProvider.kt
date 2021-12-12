package com.example.calendarkt.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private var instance : Retrofit? = null
    private val BASE_URL = "http://13.209.87.43:5000"

    fun getInstance(): Retrofit{
        if (instance == null){
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

    fun calenderApi() : CalenderApi{
        return getInstance().create(CalenderApi::class.java)
    }

}