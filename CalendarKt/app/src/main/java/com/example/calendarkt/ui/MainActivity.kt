package com.example.calendarkt.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.calendarkt.R
import com.example.calendarkt.model.GetDiaryResponse
import com.google.android.material.navigation.NavigationView
import com.example.calendarkt.network.ApiProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import token

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG:String = "MainActivity"
    val arrayList = ArrayList<GetDiaryResponse>()
    var id_pk = 0
    var title = ""
    var content = ""
    var created_at = ""
    var inherence = ""
    var nick = ""
    var favorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var total : Int = 0
        var totalS : String = ""
        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        val naviView:NavigationView = findViewById(R.id.naviView)
        val calendar : CalendarView = findViewById(R.id.calender)
        val mainText : TextView = findViewById(R.id.mainText)
        val titleText : TextView = findViewById(R.id.titleText)
        val writeBtn : Button = findViewById(R.id.writeBtn)
        val deleteBtn : Button = findViewById(R.id.deleteBtn)
        val updateBtn : Button = findViewById(R.id.updateBtn)
        val menu_Btn : ImageView = findViewById(R.id.menu_Btn)
        var intYear:Int
        var intMonth: Int
        var intdayofMonth: Int
        val dialog : Dialog

        //달력 클릭 이벤트
        calendar.setOnDateChangeListener{view,year,month,dayOfMonth->
            if(mainText.length()==0)
            {
                writeBtn.visibility = View.VISIBLE
            }
            else if(mainText.length()!=0)
            {
                mainText.visibility = View.VISIBLE
                updateBtn.visibility = View.VISIBLE
                deleteBtn.visibility = View.VISIBLE
            }
            intYear = year
            intMonth = month+1
            intdayofMonth = dayOfMonth

            total = intYear*10000+intMonth*100+intdayofMonth

            val apiProvider = ApiProvider.calenderApi().getDiary(token,total)
            apiProvider.enqueue(object : Callback<GetDiaryResponse> {
                override fun onResponse(call: Call<GetDiaryResponse>, response: Response<GetDiaryResponse>) {
                    Log.d(TAG, "onResponse: "+response.code())
                    if (response.code() == 200) {
                        if (response.body()!!.posts.size != 0) {
                            writeBtn.visibility = View.INVISIBLE
                            mainText.visibility = View.VISIBLE
                            updateBtn.visibility = View.VISIBLE
                            deleteBtn.visibility = View.VISIBLE
                            titleText.visibility = View.VISIBLE
                            id_pk = response.body()!!.posts[0].id_pk
                            title = response.body()!!.posts[0].title
                            content = response.body()!!.posts[0].content
                            created_at = response.body()!!.posts[0].created_at
                            inherence = response.body()!!.posts[0].inherence
                            nick = response.body()!!.posts[0].nick
                            favorites = response.body()!!.posts[0].Favorites
                            mainText.text = content
                            titleText.text = title

                        } else {
                            writeBtn.visibility = View.VISIBLE
                            mainText.visibility = View.INVISIBLE
                            mainText.text = ""
                            titleText.visibility = View.INVISIBLE
                            titleText.text = ""
                            updateBtn.visibility = View.INVISIBLE
                            deleteBtn.visibility = View.INVISIBLE
                        }
                    }
                }

                override fun onFailure(call: Call<GetDiaryResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }

            })

        }



        writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            val stotal = total.toString()
            intent.putExtra("date",stotal)
            intent.putExtra("favorites",favorites)
            startActivity(intent)
        }

        deleteBtn.setOnClickListener {
            val apiProvider = ApiProvider.calenderApi().deleteDiary(token, total)
            apiProvider.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: " + response.code())
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }

            })
        }

        updateBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            intent.putExtra("title",title)
            intent.putExtra("content",content)
            intent.putExtra("date",total.toString())
            intent.putExtra("favorites",favorites)
            intent.putExtra("patch",true)
            startActivity(intent)
        }

        menu_Btn.setOnClickListener{
            drawer_Layout.openDrawer(GravityCompat.START)
        }
        naviView.setNavigationItemSelectedListener(this)

    }
    private fun checkDay(year: Int, month: Int, dayOfMonth: Int, userID: String) {

    }

    @SuppressLint
    private fun saveDiary(readDay: String?) {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuLogout -> In()
            R.id.highlight -> startActivity(Intent(this,HighLightActivity::class.java))
        }
        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        drawer_Layout.closeDrawers()
        return false
    }

    private fun In(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}

