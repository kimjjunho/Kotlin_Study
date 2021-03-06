package com.example.functionfcm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivivty"
    lateinit var str : String

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn :Button = findViewById(R.id.button)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{ task ->
            if(!task.isSuccessful){
                Log.w(TAG, "onCreate: Fetching FCM registration token failed",task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val msg = getString(R.string.msg_token_fmt, token)
            str = msg
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        btn.setOnClickListener {
            Log.d(TAG, "onCreate: $str")
        }
    }
}