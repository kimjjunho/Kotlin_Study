package com.example.calendarkt

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_second_number_setting.*
import org.w3c.dom.Text

class SecondNumberSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_number_setting)

        val actionBar: ActionBar?=supportActionBar
        actionBar?.hide()

        var a : Int = 0
        var count : Int = 0;
        val settingBack_Btn : Button = findViewById(R.id.settingBack_Btn)
        val numDelBtn : Button = findViewById(R.id.numDelBtn)
        val numCreateBtn : Button = findViewById(R.id.numCreateBtn)
        var main : FrameLayout = findViewById(R.id.main)
        /*val delBtn1 : Button = findViewById(R.id.delBtn1)
        val delBtn2 : Button = findViewById(R.id.delBtn2)
        val delBtn3 : Button = findViewById(R.id.delBtn3)
        val delBtn4 : Button = findViewById(R.id.delBtn4)
        val delBtn5 : Button = findViewById(R.id.delBtn5)
        val delBtn6 : Button = findViewById(R.id.delBtn6)
        val delBtn7 : Button = findViewById(R.id.delBtn7)
        val delBtn8 : Button = findViewById(R.id.delBtn8)
        val delBtn9 : Button = findViewById(R.id.delBtn9)
        val delCheackText : TextView = findViewById(R.id.delCheackText)
        val delOneDel : Button = findViewById(R.id.delOneDel)
        val delAllDel : Button = findViewById(R.id.delAllDel)
        val delCheackBtn : Button = findViewById(R.id.delcheackBtn)
        val settingBtn1 : Button = findViewById(R.id.settingBtn1)
        val settingBtn2 : Button = findViewById(R.id.settingBtn2)
        val settingBtn3 : Button = findViewById(R.id.settingBtn3)
        val settingBtn4 : Button = findViewById(R.id.settingBtn4)
        val settingBtn5 : Button = findViewById(R.id.settingBtn5)
        val settingBtn6 : Button = findViewById(R.id.settingBtn6)
        val settingBtn7 : Button = findViewById(R.id.settingBtn7)
        val settingBtn8 : Button = findViewById(R.id.settingBtn8)
        val settingBtn9 : Button = findViewById(R.id.settingBtn9)
        val settingCheackText : TextView = findViewById(R.id.settingCheackText)
        val settingOneDel : Button = findViewById(R.id.settingOneDel)
        val settingAllDel : Button = findViewById(R.id.settingAllDel)
        val settingCheackBtn : Button = findViewById(R.id.settingCheackBtn)


        delBtn1.setOnClickListener {
            count++
            if(count>4)
            {
                Toast.makeText(applicationContext,"4자리 숫자를 입력하시오", Toast.LENGTH_SHORT).show()
            }
            else
            {
                a = a*10+1
                delCheackText.setText(a)
            }
        }*/

        numDelBtn.setOnClickListener {
            numDelBtn.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.mint))
            numCreateBtn.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.white))
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_in_right)
            transaction.replace(R.id.main,SecondNumberDeleteFragment()).commit()
        }

        numCreateBtn.setOnClickListener {
            numDelBtn.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.white))
            numCreateBtn.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.mint))

            var transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_left)
            transaction.replace(R.id.main,SecondNumberSettingFragment()).commit()
        }

        settingBack_Btn.setOnClickListener {
            finish()
        }

    }

}