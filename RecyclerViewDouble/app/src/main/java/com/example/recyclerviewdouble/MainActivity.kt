package com.example.recyclerviewdouble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdouble.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainList = arrayListOf(
            MainData("1"),
            MainData("2"),
            MainData("3"),
            MainData("4"),
            MainData("5"),
            MainData("6"),
            MainData("7"),
            MainData("8"),
            MainData("9"),
            MainData("10"),
            MainData("11"),
            MainData("12"),
            MainData("13"),
            MainData("14"),
            MainData("15"),
            )

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(applicationContext, 2)

        mBinding.rV.layoutManager = gridLayoutManager
       // mBinding.rV.setHasFixedSize(true)
        mBinding.rV.adapter = MainAdapter(mainList)
    }
}