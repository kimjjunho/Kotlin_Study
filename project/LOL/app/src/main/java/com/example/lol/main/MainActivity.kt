package com.example.lol.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lol.R
import com.example.lol.adapter.ChalAdapter
import com.example.lol.adapter.GMAdapter
import com.example.lol.adapter.MasterAdapter
import com.example.lol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        MasterEvent()

        binding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rV.setHasFixedSize(true)

        binding.mBtn.setOnClickListener {
            master()
            vm.master()
        }
        binding.gmBtn.setOnClickListener {
            gm()
            vm.gm()
        }
        binding.cBtn.setOnClickListener {
            chal()
            vm.chal()
        }
    }

    private fun MasterEvent(){
        vm.mainList.observe(this,{
            when(vm.choose){
                1->binding.rV.adapter = MasterAdapter(it)
                2->binding.rV.adapter = GMAdapter(it)
                3->binding.rV.adapter = ChalAdapter(it)
            }
        })
    }





    private fun master(){
        binding.mBtn.setTextSize(2, 15F)
        binding.mBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        binding.mView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        changeGM()
        changeChal()
    }

    private fun gm(){
        binding.gmBtn.setTextSize(2, 15F)
        binding.gmBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        binding.gmView.setBackgroundColor(ContextCompat.getColor(applicationContext!!,
            R.color.black
        ))
        changeM()
        changeChal()
    }

    private fun chal(){
        binding.cBtn.setTextSize(2,15F)
        binding.cBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.black))
        binding.cView.setBackgroundColor(ContextCompat.getColor(applicationContext!!,
            R.color.black
        ))
        changeM()
        changeGM()
    }

    private fun changeM(){
        binding.mBtn.setTextSize(2,10F)
        binding.mBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        binding.mView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

    private fun changeGM(){
        binding.gmBtn.setTextSize(2,10F)
        binding.gmBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        binding.gmView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

    private fun changeChal(){
        binding.cBtn.setTextSize(2,10F)
        binding.cBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
        binding.cView.setBackgroundColor(ContextCompat.getColor(applicationContext!!, R.color.gray))
    }

}