package com.example.recyclerviewanim.scale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R
import com.example.recyclerviewanim.alpha.AplhaAdapter
import com.example.recyclerviewanim.databinding.ActivityAlphaBinding
import com.example.recyclerviewanim.databinding.ActivityRotateBinding
import com.example.recyclerviewanim.databinding.ActivityScaleBinding

class ScaleActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityScaleBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        mBinding = ActivityScaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<Data>()
        for(i in 1..30){
            list.add(Data(i))
        }

        mBinding.rV.adapter = ScaleAdapter(list)
        mBinding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mBinding.rV.setHasFixedSize(true)
    }

}