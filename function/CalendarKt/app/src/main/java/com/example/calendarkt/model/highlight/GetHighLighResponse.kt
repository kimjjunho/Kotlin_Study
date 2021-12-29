package com.example.calendarkt.model.highlight

import com.google.gson.JsonObject

data class GetHighLighResponse(val posts:ArrayList<JsonObject>){
    class GetHighLighData(val title:String, val content:String, val url:String)
}