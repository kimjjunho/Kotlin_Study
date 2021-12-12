package com.example.calendarkt.model.login

/*data class LoginResponse(
        val login : ArrayList<LoginData>){
    class LoginData(
            var access_token: String = "0",
            val refresh_token: String)
}*/
data class LoginResponse(val access_token:String, val refresh_token:String)
