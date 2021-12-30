package com.example.lol.main

import com.google.gson.JsonObject
import org.json.JSONObject

/*data class MainResponse(val posts:ArrayList<JsonObject>){
    class MainData(val score:String, val nick:String)
}*/
data class MainResponse(val nick : String, val score : String)