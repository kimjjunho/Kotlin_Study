package com.example.intentanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.intentanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rBtn.setOnClickListener {
            startActivity(Intent(this,SubActivity::class.java))
            overridePendingTransition(R.anim.slide_left_from_right,R.anim.none)
        }
        binding.lBtn.setOnClickListener {
            startActivity(Intent(this,SubActivity::class.java))
            overridePendingTransition(R.anim.slide_right_from_left,R.anim.none)
        }
        binding.tBtn.setOnClickListener {
            startActivity(Intent(this,SubActivity::class.java))
            overridePendingTransition(R.anim.slide_down_from_top,R.anim.none)
        }
        binding.uBtn.setOnClickListener {
            startActivity(Intent(this,SubActivity::class.java))
            overridePendingTransition(R.anim.slide_up_from_bottom,R.anim.none)
        }
    }
}