package com.example.calendarkt.ui

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
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
import android.widget.*
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.example.calendarkt.R
import com.example.calendarkt.model.GetPhotoResponse
import kotlinx.android.synthetic.main.activity_write.*
import com.example.calendarkt.network.ApiProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import com.example.calendarkt.model.MakeDiaryRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.*
import token

class WriteActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String
    val REQUEST_GALLERY_TAKE = 2
    val TAG : String = "WriteActivity"
    private var syear = ""
    private var smonth = ""
    private var sday = ""
    private var date = ""
    private var highLight = false
    var title = ""
    var content = ""

    private var fileUpload: Boolean = false

    private lateinit var filetoUpload: MultipartBody.Part


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        findViewById<TextView>(R.id.addImage).setOnClickListener {
            setPhoto()
        }

        var picktureNum : Int = 0
        val mainText : EditText = findViewById(R.id.mainText)
        val titleText : EditText = findViewById(R.id.titleText)
        val yearText : TextView = findViewById(R.id.year)
        val monthText : TextView = findViewById(R.id.month)
        val dayText : TextView = findViewById(R.id.day)
        val imageButton : ImageButton = findViewById(R.id.imageButton)
        val imageView : ImageView = findViewById(R.id.imageView)

        setText()

        titleText.setText(title)
        mainText.setText(content)

        if(highLight){
            imageButton.setBackgroundResource(R.drawable.ic_baseline_local_redfire_department_24)
        }else{
            imageButton.setBackgroundResource(R.drawable.ic_baseline_local_fire_department_24)
        }

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
            if(highLight) {
                val apiProvider = ApiProvider.calenderApi().deleteHighLigh(token,date.toInt())
                apiProvider.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d(TAG, "onResponse: "+response.code())
                        if(response.code()==200){
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_local_fire_department_24)
                            highLight = false
                        }
                    }
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d(TAG, "onFailure: 실패")
                    }
                })
            } else {
                val apiProvider = ApiProvider.calenderApi().addHighLigh(token,date.toInt())
                apiProvider.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d(TAG, "onResponse: " + response.body())
                        if (response.code() == 200) {
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_local_redfire_department_24)
                            highLight = true
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d(TAG, "onFailure: 실패")
                    }

                })
            }
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
                        Toast.makeText(applicationContext,"1:1 사진 비율을 권장합니다",Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        picktureNum = 1
                        Toast.makeText(applicationContext,"1:1 사진 비율을 권장합니다",Toast.LENGTH_LONG).show()
                    }
                }

                saveBtn.setOnClickListener {
                    uploadPhoto()
                    val mt = mainText.text.toString()
                    val tt = titleText.text.toString()
                    if(tt.isNotEmpty() && mt.isNotEmpty())
                    {
                        val makeDiaryRequest = MakeDiaryRequest(tt,mt,date)
                        val apiProvider = ApiProvider.calenderApi().makeDiary(token,makeDiaryRequest)
                        apiProvider.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                Log.d(TAG, "onResponse: "+response.code())
                                if(response.code()==201){
                                    mainText.setText("")
                                    titleText.setText("")
                                    finish()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d(TAG, "onFailure: 실패")
                            }

                        })
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"제목과 본문을 모두 입력해 주세요",Toast.LENGTH_SHORT).show()
                    }

                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun uploadPhoto() {
        if(fileUpload) {
            val apiProvider = ApiProvider.calenderApi().givePoto(token, "임시", date, filetoUpload)
            apiProvider.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "사진 업로드를 성공했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: 실패")
                }

            })
        } else Toast.makeText(applicationContext, "사진을 엎로드 해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val apiProvider = ApiProvider.calenderApi().getPoto(token,date)
        apiProvider.enqueue(object : Callback<GetPhotoResponse> {
            override fun onResponse(
                call: Call<GetPhotoResponse>,
                response: Response<GetPhotoResponse>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: "+response.body()?.url)
                    val pictureMemory = response.body()?.url
                    Glide.with(applicationContext)
                        .load(pictureMemory)
                        .into(imageView)
                }
            }

            override fun onFailure(call: Call<GetPhotoResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: 실패")
            }

        })

    }

    private fun setPhoto() {

    }

    private fun setText(){
        val intent : Intent = intent
        title = intent.getStringExtra("title").toString()
        content = intent.getStringExtra("content").toString()
        date = intent.getStringExtra("date").toString()
        highLight = intent.getBooleanExtra("favorites",false)
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
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
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

    @SuppressLint("Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK)  {

            val selectedImage = data?.data
            val photoUri = data?.data

            try {
                var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
                bitmap = rotateImage(bitmap, 90)

                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            fileUpload = true

            val cursor = contentResolver.query(Uri.parse(selectedImage.toString()), null, null, null, null)!!
            cursor.moveToFirst()

            val mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))

            getFile(mediaPath)
        } else {
            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_LONG).show()
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

    // photo
    private fun getFile(mediaPath: String) {
        val file = File(mediaPath)

        val requestBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        filetoUpload = MultipartBody.Part.createFormData("file", file.name, requestBody)
    }


    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun verifyStoragePermissions(activity: Activity?) {
        val permission = ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

}

