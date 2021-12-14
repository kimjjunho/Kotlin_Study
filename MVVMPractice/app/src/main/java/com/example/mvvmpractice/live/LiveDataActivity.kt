package com.example.mvvmpractice.live

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {

    private var mBinding : ActivityLiveDataBinding? = null
    private val binding get() = mBinding!!
    private val modelL: ViewModelL by viewModels()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        mBinding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.button.setOnClickListener {
            mBinding!!.text.text = "ㅅㅂ"
        }
        mBinding!!.refresh.setOnRefreshListener {
            startActivity(Intent(applicationContext, LiveDataActivity::class.java))
            finish()
            mBinding!!.refresh.isRefreshing = false
        }
    }
    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}