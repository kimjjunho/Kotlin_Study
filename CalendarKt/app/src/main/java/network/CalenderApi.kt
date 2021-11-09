package network

import android.provider.ContactsContract
import model.Login
import model.MakeId
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CalenderApi {
    @POST("/login")
    fun sendLogin(
        @Body body: Login
    ): Call<Login>

    @POST("/signup")
    fun sendMakeId(
       @Body body: MakeId
    ): Call<MakeId>

}