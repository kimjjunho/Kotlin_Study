package com.example.recyclerviewanim.translate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewanim.Data
import com.example.recyclerviewanim.R
import com.example.recyclerviewanim.alpha.AplhaAdapter
import com.example.recyclerviewanim.databinding.ActivityRotateBinding
import com.example.recyclerviewanim.databinding.ActivityScaleBinding
import com.example.recyclerviewanim.databinding.ActivityTranslateBinding

class TranslateActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityTranslateBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        mBinding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<Data>()
        for(i in 1..30){
            list.add(Data(i))

        }

        mBinding.rV.adapter = TranslateAdapter(list)
        mBinding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mBinding.rV.setHasFixedSize(true)

    }
}