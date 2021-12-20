package com.example.recyclerviewanim.alpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R
import com.example.recyclerviewanim.databinding.ActivityAlphaBinding
import com.example.recyclerviewanim.databinding.ActivityMainBinding

class AlphaActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityAlphaBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alpha)

        mBinding = ActivityAlphaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<Data>()
        for(i in 1..30){
            list.add(Data(i))
        }

        mBinding.rV.adapter = AplhaAdapter(list)
        mBinding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mBinding.rV.setHasFixedSize(true)
    }
}