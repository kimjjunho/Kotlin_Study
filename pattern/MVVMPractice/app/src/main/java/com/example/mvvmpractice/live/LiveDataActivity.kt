package com.example.mvvmpractice.live

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpractice.R
import com.example.mvvmpractice.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModelL:ViewModelL
    private lateinit var mBinding : ActivityLiveDataBinding
    private val binding get() = mBinding
    private val TAG = "LiveDataActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        mBinding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelL = ViewModelProvider(this)[ViewModelL::class.java]
        
        viewModelL.currentValue.observe(this, Observer {
            Log.d(TAG, "onCreate: currentValue 라이브 데이터 값 변경")
            mBinding.textView.text = it.toString()
        })

        mBinding.addBtn.setOnClickListener(this)
        mBinding.minBtn.setOnClickListener(this)

    }



    override fun onClick(view: View?) {
        val userInput = mBinding.editText.text.toString().toInt()

        when(view){
            mBinding.addBtn-> viewModelL.updateValue(actionType = ActionType.PLUS, userInput)
            mBinding.minBtn-> viewModelL.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }

}