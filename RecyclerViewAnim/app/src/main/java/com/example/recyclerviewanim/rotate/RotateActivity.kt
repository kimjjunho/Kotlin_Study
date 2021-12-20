package com.example.recyclerviewanim.rotate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R
import com.example.recyclerviewanim.alpha.AplhaAdapter
import com.example.recyclerviewanim.databinding.ActivityAlphaBinding
import com.example.recyclerviewanim.databinding.ActivityRotateBinding

class RotateActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityRotateBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)

        mBinding = ActivityRotateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<Data>()
        for(i in 1..30){
            list.add(Data(i))
        }

        mBinding.rV.adapter = RotateAdapter(list)
        mBinding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mBinding.rV.setHasFixedSize(true)

    }
}