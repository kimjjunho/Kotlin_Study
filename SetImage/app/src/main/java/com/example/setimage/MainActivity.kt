package com.example.setimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val OPEN_GALLERY = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val open_btn : Button = findViewById(R.id.open_btn)

        open_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openGallery()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val main_image : ImageView = findViewById(R.id.main_image)

        if(resultCode == Activity.RESULT_OK){
            var currentImageUrl:Uri?= data?.data

            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUrl)
                main_image.setImageBitmap(bitmap)
            }catch (e:Exception){
                e.printStackTrace()
            }
           /* if(resultCode == OPEN_GALLERY){


            }
            else{
                Toast.makeText(this,"MainActivitySomeWrong",Toast.LENGTH_SHORT).show()
            }*/
        }
    }

    private fun openGallery(){
        val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,OPEN_GALLERY)
    }
}