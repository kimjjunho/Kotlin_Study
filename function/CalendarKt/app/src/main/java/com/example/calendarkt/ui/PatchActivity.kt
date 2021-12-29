package com.example.calendarkt.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.calendarkt.R
import com.example.calendarkt.model.MakeDiaryRequest
import com.example.calendarkt.model.PatchDiaryRequest
import com.example.calendarkt.network.ApiProvider
import kotlinx.android.synthetic.main.activity_write.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import token
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PatchActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String
    val REQUEST_GALLERY_TAKE = 2
    val TAG : String = "WriteActivity"
    private var syear = ""
    private var smonth = ""
    private var sday = ""
    private var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patch)

        var picktureNum : Int = 0
        val mainText : EditText = findViewById(R.id.mainText)
        val titleText : EditText = findViewById(R.id.titleText)
        val yearText : TextView = findViewById(R.id.year)
        val monthText : TextView = findViewById(R.id.month)
        val dayText : TextView = findViewById(R.id.day)

        setText()
        yearText.text = "$syear."
        monthText.text = "$smonth."
        dayText.text = sday
        addImage.setOnClickListener {
            if(picktureNum == 0) {
                if (checkPersmission()) {
                    dispatchTakePictureIntent()
                } else {
                    requestPermission()
                }
            }
            else{
                if(checkPersmission()){
                    openGalleryForImage()
                }
                else{
                    requestPermission()
                }
            }
        }

        imageButton.setOnClickListener{
            imageButton.setBackgroundResource(R.drawable.ic_baseline_local_redfire_department_24)
        }

        back.setOnClickListener {
            finish()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        picktureNum = 0
                        Toast.makeText(applicationContext,"1:1 사진 비율을 권장합니다", Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        picktureNum = 1
                        Toast.makeText(applicationContext,"1:1 사진 비율을 권장합니다", Toast.LENGTH_LONG).show()
                    }
                }

                saveBtn.setOnClickListener {
                    val mt = mainText.text.toString()
                    val tt = titleText.text.toString()
                    if(tt.isNotEmpty() && mt.isNotEmpty())
                    {
                        val patchDiaryRequest = PatchDiaryRequest(tt,mt)
                        val apiProvider = ApiProvider.calenderApi().patchDiary(token,date.toInt(),patchDiaryRequest)
                        apiProvider.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                Log.d(TAG, "onResponse: "+response.code())
                                if(response.code()==201){
                                    mainText.setText("")
                                    titleText.setText("")
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d(TAG, "onFailure: 실패")
                            }

                        })
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"제목과 본문을 모두 입력해 주세요", Toast.LENGTH_SHORT).show()
                    }

                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun setText(){
        val intent : Intent = getIntent()
        date = intent.getStringExtra("date").toString()
        Log.d(TAG, "onCreate: "+date)
        val idate = date.toInt()
        val year = idate/10000
        syear = year.toString()
        val month = (idate-year*10000)/100
        smonth = month.toString()
        val day = idate-year*10000-month*100
        sday = day.toString()
    }

    // 카메라 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ),
            REQUEST_IMAGE_CAPTURE)
    }

    // 카메라 권한 체크
    private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    // 권한요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "Permission: " + permissions[0] + "was " + grantResults[0] + "카메라 허가 받음 예이^^")
        }else{
            Log.d("TAG","카메라 허가 못받음 ㅠ 젠장!!")
        }
    }
    // 카메라 열기
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                // 찍은 사진을 그림파일로 만들기
                val photoFile: File? =
                    try {
                        createImageFile()
                    } catch (ex: IOException) {
                        Log.d("TAG", "그림파일 만드는도중 에러생김")
                        null
                    }

                // 그림파일을 성공적으로 만들었다면 onActivityForResult로 보내기
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this, "com.example.calendarkt.fileprovider", it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }


    // 카메라로 촬영한 이미지를 파일로 저장해준다
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            1 -> {
                if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){

                    // 카메라로부터 받은 데이터가 있을경우에만
                    val file = File(currentPhotoPath)
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media
                            .getBitmap(contentResolver, Uri.fromFile(file))  //Deprecated
                        imageView.setImageBitmap(rotateImageIfRequired(file.path))
                    }
                    else{
                        val decode = ImageDecoder.createSource(this.contentResolver,
                            Uri.fromFile(file))
                        val bitmap = ImageDecoder.decodeBitmap(decode)
                        imageView.setImageBitmap(rotateImageIfRequired(file.path))
                    }
                }
            }
            2 -> {
                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE){
                    imageView.setImageURI(data?.data) // handle chosen image
                }
            }
        }
    }

    private fun rotateImageIfRequired(imagePath: String): Bitmap? {
        var degrees = 0
        try {
            val exif = ExifInterface(imagePath)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degrees = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degrees = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degrees = 270
            }
        } catch (e: IOException) {
            Log.e("ImageError", "Error in reading Exif data of $imagePath", e)
        }

        val decodeBounds: BitmapFactory.Options = BitmapFactory.Options()
        decodeBounds.inJustDecodeBounds = true
        var bitmap: Bitmap? = BitmapFactory.decodeFile(imagePath, decodeBounds)
        val numPixels: Int = decodeBounds.outWidth * decodeBounds.outHeight
        val maxPixels = 2048 * 1536 // requires 12 MB heap
        val options: BitmapFactory.Options = BitmapFactory.Options()
        options.inSampleSize = if (numPixels > maxPixels) 2 else 1
        bitmap = BitmapFactory.decodeFile(imagePath, options)
        if (bitmap == null) {
            return null
        }

        val matrix = Matrix()
        matrix.setRotate(degrees.toFloat())
        bitmap = Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true
        )
        return bitmap
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_TAKE)
    }

}