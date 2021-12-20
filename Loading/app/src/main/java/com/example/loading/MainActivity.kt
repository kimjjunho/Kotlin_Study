package com.example.loading

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.loading.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // mBinding.progressBar.isIndeterminate = true
        mBinding.progressBar.indeterminateDrawable.setColorFilter(Color.rgb(60,179,113), android.graphics.PorterDuff.Mode.MULTIPLY)

        mBinding.btn.setOnClickListener {
            showProgress(true)
            thread(start = true) {
                Thread.sleep(3000)
                runOnUiThread {
                    showProgress(false)
                }
            }
        }
    }


    fun showProgress(isShow : Boolean){
        if(isShow){
            mBinding.lodingLayout.visibility = View.VISIBLE
        }else{
            mBinding.lodingLayout.visibility = View.GONE
        }
    }
}