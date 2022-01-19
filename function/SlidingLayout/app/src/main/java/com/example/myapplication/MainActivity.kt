package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFrame : SlidingUpPanelLayout = findViewById(R.id.mainFrame)
        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            mainFrame.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        }
    }
}