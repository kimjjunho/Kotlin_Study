package com.example.mvvmpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmpractice.bind.DataBindingActivity
import com.example.mvvmpractice.databinding.ActivityDataBindingBinding
import com.example.mvvmpractice.live.LiveDataActivity
import com.example.mvvmpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.bindBtn.setOnClickListener {
            startActivity(Intent(this, DataBindingActivity::class.java))
        }
        mBinding!!.liveBtn.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
    }
}