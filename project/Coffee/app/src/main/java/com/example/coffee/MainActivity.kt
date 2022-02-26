package com.example.coffee

import android.app.Person
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonArray
import dataList
import org.json.JSONArray
import org.json.JSONObject
import total_minus
import total_plus
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private val binding get() = mBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding.addBtn.setOnClickListener {
            dataList.add(MainData("","","","",false))
            mBinding.rV.adapter = MainAdapter(dataList)
        }
        mBinding.totalBtn.setOnClickListener {
            mBinding.rV.adapter = MainAdapter(dataList)
            if(total_plus<0){
                total_plus = 0
            }
            if(total_minus<0){
                total_minus = 0
            }
            mBinding.totalPlus.text = total_plus.toString()
            mBinding.totalMinus.text = total_minus.toString()
            mBinding.totalTotal.text = (total_plus - total_minus).toString()
        }
        mBinding.saveBtn.setOnClickListener {
            saveData()
        }
        mBinding.rV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mBinding.rV.setHasFixedSize(true)
    }

    private fun loadData(){
        val pref = getSharedPreferences("pref",0)
        val str = pref.getString("name","0")
        println(str)
        if(str != "0"){
            val jsonObject = JSONObject(str.toString())
            val posts = jsonObject.getString("posts")
            val jsonArray = JSONArray(posts)
            if(jsonArray.length()==0){
                return
            }
            for(i in 0 until jsonArray.length()){
                val jsonObject1 = jsonArray.getJSONObject(i)
                val date = jsonObject1.getString("date")
                val plus = jsonObject1.getString("plus")
                val minus = jsonObject1.getString("minus")
                val total = jsonObject1.getString("total")
                val click = jsonObject1.getString("click")
                var click1 = true
                if(click != "true")
                    click1 = false
                else
                    click1 = true

                val mainData = MainData(date, plus, minus, total, click1)
                dataList.add(mainData)
            }
        }
    }

    fun saveData(){
        val obj = JSONObject()
        val jArray = JSONArray()

        for(i in 0 until dataList.size){
            val sObject = JSONObject()
            sObject.put("date", dataList[i].date)
            sObject.put("plus",dataList[i].plus)
            sObject.put("minus",dataList[i].minus)
            sObject.put("total",dataList[i].total)
            sObject.put("click",dataList[i].click)
            jArray.put(sObject)
        }
        obj.put("posts",jArray)

        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("name",obj.toString())
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }
}