package com.example.intentanimation

import android.content.Intent
import android.icu.number.Scale.none
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentanimation.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivitySubBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        mBinding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.finish_right_from_left)
        }

        binding.rBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.finish_left_from_right)
        }

        binding.uBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.finish_up_from_bottom)
        }

        binding.tBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.finish_down_from_top)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}