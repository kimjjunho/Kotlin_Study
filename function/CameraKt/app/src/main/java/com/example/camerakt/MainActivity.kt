package com.example.camerakt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1 //카메라 사진 촬영 요청
    lateinit var curPhothoPath: String //문자열 형태의 사진 경로 값(초기 값을 null로 시작하고 싶을 때)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val camera_btn : Button = findViewById(R.id.camera_btn)
        val camera_profile : ImageView = findViewById(R.id.camera_profile)

        setPermission() //최초의 권한을 check하는 메서드 실행

        camera_btn.setOnClickListener{
            takeCapture()
        }

    }

    private fun takeCapture() {
        //기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try{
                    createImageFile()
                }catch (ex: IOException){
                    null
                }
                photoFile?.also{
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.camerakt.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

                }
            }
        }
    }

    private fun createImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_",".JPG",storageDir)
            .apply { curPhothoPath = absolutePath}
    }

    private fun setPermission() {
        val permission = object : PermissionListener{
            override fun onPermissionGranted() { //설정해 놓은 위험 권한들이 허용 되었을 경우 실행
                Toast.makeText(this@MainActivity,"권한 허용 됨",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { //설정해 놓은 위험 권한 들 중 거부를 한 경우 실행
                Toast.makeText(this@MainActivity,"권한 허용 되지 않음",Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 사용해주세요")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정]->[권한] 항목에서 허용해주세요.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()
    }

    //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과 값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val camera_profile : ImageView = findViewById(R.id.camera_profile)

        //이미지를 성공적으로 가져왔다면...
        if(requestCode == REQUEST_IMAGE_CAPTURE&&resultCode==Activity.RESULT_OK){
            val bitmap: Bitmap
            val file = File(curPhothoPath)
            if(Build.VERSION.SDK_INT<28){ //안드로이드 9.0버전보다 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver,Uri.fromFile(file))
                camera_profile.setImageBitmap(bitmap)
            }else{//안드로이드 9.0보다 높을 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap=ImageDecoder.decodeBitmap(decode)
                camera_profile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }
    }

    private fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath+"/Pictures/"
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder =  File(folderPath)
        if(!folder.isDirectory){ //현재 해당 경로에 폴더가 존재하지 않는다면
            folder.mkdirs() //make directory 줄임말로 해당 경로에 폴더 자동으로 새로 만들기
        }

        val out = FileOutputStream(folderPath+fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
        Toast.makeText(this,"사진이 앨범에 저장되었습니다",Toast.LENGTH_SHORT).show()
    }
}