package com.example.lol

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import com.example.lol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding.mBtn.setOnClickListener {
            master()
        }
        mBinding.gmBtn.setOnClickListener {
            gm()
        }
        mBinding.cBtn.setOnClickListener {
            chal()
        }
    }

    private fun master(){
        mBinding.mBtn.setTextSize(2, 15F)
        mBinding.mBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        mBinding.mView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        changeGM()
        changeChal()
    }

    private fun gm(){
        mBinding.gmBtn.setTextSize(2, 15F)
        mBinding.gmBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        mBinding.gmView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        changeM()
        changeChal()
    }

    private fun chal(){
        mBinding.cBtn.setTextSize(2,15F)
        mBinding.cBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        mBinding.cView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        changeM()
        changeGM()
    }

    private fun changeM(){
        mBinding.mBtn.setTextSize(2,10F)
        mBinding.mBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        mBinding.mView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

    private fun changeGM(){
        mBinding.gmBtn.setTextSize(2,10F)
        mBinding.gmBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        mBinding.gmView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

    private fun changeChal(){
        mBinding.cBtn.setTextSize(2,10F)
        mBinding.cBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        mBinding.cView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

}