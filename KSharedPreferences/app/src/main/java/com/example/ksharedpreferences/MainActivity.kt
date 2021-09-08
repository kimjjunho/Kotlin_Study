package com.example.ksharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.btn)
        btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                saveData()
            }
        })

        loadData()
    }

    private fun loadData() {
        val nameText : EditText = findViewById(R.id.nameText)

        val pref = getSharedPreferences("pref",0)
        nameText.setText(pref.getString("name","sdsdadad"))

    }

    private fun saveData() {
        val nameText : EditText = findViewById(R.id.nameText)

        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("name", nameText.text.toString())
        edit.apply()
    }

    override fun onDestroy() {
        super.onDestroy()

        saveData()
    }


}