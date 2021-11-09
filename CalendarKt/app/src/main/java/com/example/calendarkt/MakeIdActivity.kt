package com.example.calendarkt

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MakeIdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_id)

        val actionBar: ActionBar?=supportActionBar
        actionBar?.hide()

        val back : Button = findViewById(R.id.back)

        back.setOnClickListener {
            finish()
        }
    }
}