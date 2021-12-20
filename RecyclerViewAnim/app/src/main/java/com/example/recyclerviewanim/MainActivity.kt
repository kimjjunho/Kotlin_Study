package com.example.recyclerviewanim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewanim.alpha.AlphaActivity
import com.example.recyclerviewanim.databinding.ActivityMainBinding
import com.example.recyclerviewanim.rotate.RotateActivity
import com.example.recyclerviewanim.scale.ScaleActivity
import com.example.recyclerviewanim.translate.TranslateActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding.alpha.setOnClickListener {
            startActivity(Intent(applicationContext,AlphaActivity::class.java))
        }
        mBinding.rotate.setOnClickListener {
            startActivity(Intent(applicationContext,RotateActivity::class.java))
        }
        mBinding.scale.setOnClickListener {
            startActivity(Intent(applicationContext,ScaleActivity::class.java))
        }
        mBinding.tran.setOnClickListener {
            startActivity(Intent(applicationContext,TranslateActivity::class.java))
        }

    }
}