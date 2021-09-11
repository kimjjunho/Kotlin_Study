package com.example.recyclerviewwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val main_rV : RecyclerView = findViewById(R.id.main_rv)
        val send_btn : Button = findViewById(R.id.send_btn)
        val main_text : EditText = findViewById(R.id.main_text)
        var name : String = ""
        val mainList = ArrayList<MainData>();

        send_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                name = main_text.text.toString()
                if(name != "") {
                    mainList.add(MainData(name))
                    main_rV.adapter = MainAdapter(mainList)
                }
                else{
                    Toast.makeText(this@MainActivity,"텍스틀를 입력하시오",Toast.LENGTH_LONG).show()
                }
            }

        })
        main_rV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        main_rV.setHasFixedSize(true)

    }
}