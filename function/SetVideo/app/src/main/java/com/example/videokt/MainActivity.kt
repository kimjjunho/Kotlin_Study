package com.example.videokt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vvBtn : Button = findViewById(R.id.vvBtn)

        vvBtn.setOnClickListener {
            startActivity(Intent(applicationContext,ViewVideoActivity::class.java))
        }
    }
}
