package com.example.calendarkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val helpBack_Btn : Button = findViewById(R.id.helpBack_Btn)

        var actionBar:ActionBar? = supportActionBar
        actionBar?.hide()

        helpBack_Btn.setOnClickListener {
            finish()
        }


    }
}