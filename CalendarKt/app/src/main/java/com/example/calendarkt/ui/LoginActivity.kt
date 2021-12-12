package com.example.calendarkt.ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.biometric.BiometricPrompt
import com.example.calendarkt.R
import com.example.calendarkt.model.login.LoginRequest
import com.example.calendarkt.model.login.LoginResponse
import com.example.calendarkt.network.ApiProvider
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import token
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginId : EditText = findViewById(R.id.loginId)
        val loginPassword : EditText = findViewById(R.id.loginPassword)
        val login: Button = findViewById(R.id.login)
        val checkBox: CheckBox = findViewById(R.id.checkbox)
        val makeId:Button = findViewById(R.id.makeId)

        if (checkBox.isChecked) {
            loginPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        checkBox.setOnCheckedChangeListener { _checkBox, isChecked ->
            if (isChecked) {
                loginPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        login.setOnClickListener {
            val id = loginId.text.toString()
            val password = loginPassword.text.toString()
            val loginRequest = LoginRequest(id,password)
            val apiProvider = ApiProvider.calenderApi().sendLogin(loginRequest)
            apiProvider.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d(TAG, "onResponse: " + response.code())
                    if (response.code() == 201) {
                        //Log.d(TAG, "onResponse: "+ (response.body()!!.login[0].access_token))
                        Log.d(TAG, "onResponse: "+ (response.body()?.access_token))
                        token = "Bearer "+response.body()!!.access_token
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }
                    if (response.code() == 409) {
                        Toast.makeText(applicationContext, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }
            })
        }

        makeId.setOnClickListener {
            startActivity(Intent(this, MakeIdActivity::class.java))
        }

    }


}