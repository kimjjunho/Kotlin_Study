package com.example.calendarkt.network

import com.example.calendarkt.model.*
import com.example.calendarkt.model.highlight.GetHighLighResponse
import com.example.calendarkt.model.login.LoginRequest
import com.example.calendarkt.model.login.LoginResponse
import com.example.calendarkt.model.makeId.MakeIdRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CalenderApi {
    @POST("/login")
    fun sendLogin(
            @Body body: LoginRequest
    ): Call<LoginResponse>

    @POST("/signup")
    fun sendMakeId(
            @Body body: MakeIdRequest
    ): Call<Void>

    @POST("/post")
    fun makeDiary(
            @Header("Authorization") header: String,
            @Body body:MakeDiaryRequest
    ):Call<Void>


    @GET("/post")
    fun getDiary(
            @Header("Authorization") header: String,
            @Query("inherence") inherence: Int
    ):Call<GetDiaryResponse>

    @DELETE("/post/{id}")
    fun deleteDiary(
            @Header("Authorization") header: String,
            @Path("id") id:Int
    ):Call<Void>

    @PATCH("/post")
    fun patchDiary(
            @Header("Authorization") header:String,
            @Path("id") id:Int,
            @Body body: PatchDiaryRequest
    ):Call<Void>

    @POST("/Favorites/{id}")
    fun addHighLigh(
            @Header("Authorization") header: String,
            @Path("id") id:Int
    ):Call<Void>

    @GET("/Favorites")
    fun getHighLigh(
            @Header("Authorization")header: String
    ):Call<GetHighLighResponse>

    @DELETE("/Favorites/{id}")
    fun deleteHighLigh(
            @Header("Authorization")header: String,
            @Path("id") id:Int
    ):Call<Void>

    // 사진 추가
    @Multipart
    @POST("/poto")
    fun givePoto(
            @Header("Authorization") header: String,
            @Query("name") name : String,
            @Query("inherence") inherence : String,
            @Part file: MultipartBody.Part?
    ) : Call<Void>

    @GET("/poto")
    fun getPoto(
            @Header("Authorization") header: String,
            @Query("inherence") inherence:String
    ):Call<GetPhotoResponse>

    @POST("/auth")
    fun checkId(
            @Body nick : IdCheckRequest
    ):Call<Void>
}