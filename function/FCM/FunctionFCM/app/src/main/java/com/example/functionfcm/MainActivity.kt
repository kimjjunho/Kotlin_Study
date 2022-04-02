package com.example.functionfcm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivivty"

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{ task ->
            if(!task.isSuccessful){
                Log.w(TAG, "onCreate: Fetching FCM registration token failed",task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val msg = getString(R.string.msg_token_fmt, token)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }
}