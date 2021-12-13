package com.example.calendarkt.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarkt.R
import com.example.calendarkt.model.IdCheckRequest
import com.example.calendarkt.model.makeId.MakeIdRequest
import com.example.calendarkt.network.ApiProvider
import kotlinx.android.synthetic.main.activity_make_id.*
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeIdActivity : AppCompatActivity() {

    private val TAG = "MakeIdActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_id)

        val makeId : Button = findViewById(R.id.makeId)
        val back : Button = findViewById(R.id.back)
        val id : EditText = findViewById(R.id.id)
        val password : EditText = findViewById(R.id.password)
        val nick : EditText = findViewById(R.id.nick)

        makeId.setOnClickListener {
            val i = id.text.toString()
            val p = password.text.toString()
            val n = nick.text.toString()
            val makeIdRequest = MakeIdRequest(n,p,i)

            val apiProvider = ApiProvider.calenderApi().sendMakeId(makeIdRequest)
            apiProvider.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: "+response.code())
                    if(response.code() == 201){
                        finish()
                    }
                    if(response.code() == 409){
                        Toast.makeText(applicationContext,"항목을 정확하게 입력해 주세요",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }

            })
        }
        idCheack.setOnClickListener {
            val idCheckRequest = IdCheckRequest(id.text.toString())
            val apiProvider = ApiProvider.calenderApi().checkId(idCheckRequest)
            apiProvider.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: "+response.code())
                    if(response.code()==200){
                        Toast.makeText(applicationContext,"아이디 사용 가능",Toast.LENGTH_SHORT).show()
                    }
                    if (response.code() == 409) {
                        Toast.makeText(applicationContext, "아이디 중복", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }
            })
        }
        back.setOnClickListener {
            finish()
        }
    }
}