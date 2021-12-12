package com.example.calendarkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListAdapter
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarkt.R
import com.example.calendarkt.model.highlight.GetHighLighResponse
import com.example.calendarkt.model.highlight.HighLightAdapter
import com.example.calendarkt.network.ApiProvider
import kotlinx.android.synthetic.main.activity_high_light.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import token

class HighLightActivity : AppCompatActivity() {

    val TAG = "HighLightActivity"
    private val highLightList = ArrayList<GetHighLighResponse.GetHighLighData>()
    private var title = ""
    private var content = ""
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_light)
        val button : Button = findViewById(R.id.button)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)

        val apiProvider = ApiProvider.calenderApi().getHighLigh(token)
        apiProvider.enqueue(object : Callback<GetHighLighResponse> {
            override fun onResponse(call: Call<GetHighLighResponse>, response: Response<GetHighLighResponse>) {
                Log.d(TAG, "onResponse: "+response.code())
                if (response.code() == 200) {
                    if (response.body()!!.posts.size != 0) {
                        Log.d(TAG, "onResponse: " + "{\n" + "\"posts\":" + response.body()!!.posts + "}")
                        showitem("{\n" + "\"posts\":" + response.body()!!.posts + "}", response)
                        recyclerView.layoutManager = LinearLayoutManager(this@HighLightActivity, LinearLayoutManager.VERTICAL, false)
                        recyclerView.setHasFixedSize(true)
                        recyclerView.adapter = HighLightAdapter(highLightList)
                    }
                }
            }

            override fun onFailure(call: Call<GetHighLighResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: 실패")
            }

        })

        button.setOnClickListener {
            finish()
        }
    }

    fun showitem(json : String, response: Response<GetHighLighResponse>)
    {
        try {
            val jsonObject = JSONObject(json)
            val posts = jsonObject.getString("posts")
            val jsonArray = JSONArray(posts)
            if(jsonArray.length()==0){
                return
            }
            for(i in 0 until jsonArray.length())
            {
                val jsonObject1 = jsonArray.getJSONObject(i)
                title = jsonObject1.getString("title")
                content = jsonObject1.getString("content")
                url = jsonObject1.getString("url")

                val highLighData = GetHighLighResponse.GetHighLighData("   $title", content,url)
                highLightList.add(highLighData)
            }
        }catch (e:JSONException){
            e.printStackTrace()
        }
    }
}