package com.example.calendarkt.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

//data class GetDiaryResponse(var posts: List<JsonObject>)

data class GetDiaryResponse(
        val posts: ArrayList<GetDiaryData>
) {
    class GetDiaryData(
            val id_pk: Int,
            val title: String,
            val content: String,
            val created_at: String,
            val inherence: String,
            val nick: String,
            val Favorites: Boolean
    )
}