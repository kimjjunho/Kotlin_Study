package com.example.calendarkt

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.BinderThread
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG:String = "MainActivity"
    val userID: String = "userID"
    lateinit var fname: String
    lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var total : Int
        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        val naviView:NavigationView = findViewById(R.id.naviView)
        val calendar : CalendarView = findViewById(R.id.calender)
        val mainText : TextView = findViewById(R.id.mainText)
        val writeBtn : Button = findViewById(R.id.writeBtn)
        val deleteBtn : Button = findViewById(R.id.deleteBtn)
        val updateBtn : Button = findViewById(R.id.updateBtn)
        val menu_Btn : ImageView = findViewById(R.id.menu_Btn)
        var intYear:Int
        var intMonth: Int
        var intdayofMonth: Int
        val dialog : Dialog

        val actionBar: ActionBar?=supportActionBar
        actionBar?.hide()


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

        }

        writeBtn.setOnClickListener {
            startActivity(Intent(this,WriteActivity::class.java))
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

    class ShareObject{
        companion object{
            var useSecondPasswordBool : Boolean = false
            var zimmonSwitchBool: Boolean = false
            var textsecondPasswordSettingBool : Boolean = false
            var textsecondPasswordNumber : Int = 0

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        val helpIntent = Intent(this,HelpActivity::class.java)
        val logOutIntent = Intent(this,LoginActivity::class.java)
        val highLightIntent = Intent(this,HelpActivity::class.java)
        val settingIntent = Intent(this, SettingActivity::class.java)
        when(item.itemId){
            R.id.menuHelp -> startActivity(helpIntent)
            R.id.menuHighlight -> startActivity(highLightIntent)
            R.id.menuLogout -> startActivity(logOutIntent)
            R.id.menuSetting -> startActivity(settingIntent)
        }
        drawer_Layout.closeDrawers()
        return false
    }


}

