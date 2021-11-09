package com.example.calendarkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar

class HighLightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_light)

        val actionBar: ActionBar?=supportActionBar
        actionBar?.hide()

        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            finish()
        }
    }
}