package com.example.buttontoggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isSelected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            if (!isSelected) {
                isSelected = true
                more_layout.visibility = View.VISIBLE
            } else {
                isSelected = false
                more_layout.visibility = View.GONE
            }
        }
    }
}