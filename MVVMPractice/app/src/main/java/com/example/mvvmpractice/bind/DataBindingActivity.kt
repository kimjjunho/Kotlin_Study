package com.example.mvvmpractice.bind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.ActivityDataBindingBinding
import com.example.mvvmpractice.databinding.ActivityMainBinding

class DataBindingActivity : AppCompatActivity() {

    private lateinit var dBinding: ActivityDataBindingBinding
   // private var mBinding : ActivityDataBindingBinding? = null
    private val binding get() = dBinding
    private val TAG = "DataBindingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)

        dBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        setContentView(binding.root)
        dBinding.vm = ViewModelD()

        dBinding!!.restart.setOnClickListener {
            Log.d(TAG, "onCreate: ㅅㅂ")
        }
    }
}