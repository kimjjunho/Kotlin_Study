package com.example.calendarkt

import android.content.Intent
import android.nfc.Tag
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.ActionBar
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_main.*

var holdSwitch : Boolean = false
val TAG : String = "SettingActivity"
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val TAG: String = "SettingActivity"
        val useSecondPassword: Switch = findViewById(R.id.useSecondPassword)
        val zimmonSwitch: Switch = findViewById(R.id.zimmonSwitch)
        val settingBackBtn: Button = findViewById(R.id.settingBackBtn)
        val secondPasswordSetting: Button = findViewById(R.id.secondPasswordSetting)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        if(MainActivity.ShareObject.useSecondPasswordBool)
        {
            useSecondPassword.isChecked = true
            zimmonSwitch.visibility = View.VISIBLE
            secondPasswordSetting.visibility = View.VISIBLE
        }
        else if(!MainActivity.ShareObject.useSecondPasswordBool)
        {
            useSecondPassword.isChecked = false
            zimmonSwitch.isChecked = false
            MainActivity.ShareObject.zimmonSwitchBool = false
            zimmonSwitch.visibility = View.INVISIBLE
            secondPasswordSetting.visibility = View.INVISIBLE
        }

        if(MainActivity.ShareObject.zimmonSwitchBool)
        {
            zimmonSwitch.isChecked = true
        }
        else if(!MainActivity.ShareObject.zimmonSwitchBool)
        {
            zimmonSwitch.isChecked = false
        }

        useSecondPassword.setOnClickListener {
            if(useSecondPassword.isChecked)
            {
                MainActivity.ShareObject.useSecondPasswordBool = true
                zimmonSwitch.visibility = View.VISIBLE
                secondPasswordSetting.visibility = View.VISIBLE
            }
            else if(!useSecondPassword.isChecked)
            {
                MainActivity.ShareObject.useSecondPasswordBool = false
                zimmonSwitch.visibility = View.INVISIBLE
                secondPasswordSetting.visibility = View.INVISIBLE
                MainActivity.ShareObject.zimmonSwitchBool = false
            }
        }

        zimmonSwitch.setOnClickListener {
            if(zimmonSwitch.isChecked)
            {
                MainActivity.ShareObject.zimmonSwitchBool = true
            }
            else if(!zimmonSwitch.isChecked)
            {
                MainActivity.ShareObject.zimmonSwitchBool = false
            }
        }

        secondPasswordSetting.setOnClickListener {
            val settingIntent = Intent(this,SecondNumberSettingActivity::class.java)
            startActivity(settingIntent)
        }

        settingBackBtn.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData(){
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()

        edit.putBoolean("useSecondPasswordBool",MainActivity.ShareObject.useSecondPasswordBool)
        edit.putBoolean("zimmonSwitchBool",MainActivity.ShareObject.zimmonSwitchBool)
        edit.putBoolean("textsecondPasswordSettingBool",MainActivity.ShareObject.textsecondPasswordSettingBool)
        edit.putInt("textsecondPasswordNumber",MainActivity.ShareObject.textsecondPasswordNumber)
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }


}

