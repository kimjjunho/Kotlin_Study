package com.example.calendarkt

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.logindialog.*
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val login: Button = findViewById(R.id.login)
        val checkBox: CheckBox = findViewById(R.id.checkbox)
        val loginPathward: EditText = findViewById(R.id.loginPassward)
        

        //  val password_toggle: TextInputLayout = findViewById(R.id.password_toggle)
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.logindialog)
        val makeId:Button = findViewById(R.id.makeId)

        val dialog_zimoon: Button = dialog.findViewById(R.id.dialog_zimoon)
        val dialog_pass: Button = dialog.findViewById(R.id.dialog_pass)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()



        loadData()

        /*if(MainActivity.ShareObject.zimmonSwitchBool)
        {
            zimoonBtn.visibility = View.VISIBLE
        }
        else if(!MainActivity.ShareObject.zimmonSwitchBool)
        {
            zimoonBtn.visibility = View.INVISIBLE
        }*/

        // loginPathward.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
//        show_tv.setOnClickListener{
//            loginPassward.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
//        }



        if (checkBox.isChecked()) {
            loginPassward.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
        } else {
            loginPassward.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
            loginPathward.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }
        login.setOnClickListener {
            if(MainActivity.ShareObject.zimmonSwitchBool||MainActivity.ShareObject.useSecondPasswordBool)
            {
               /* val intent = Intent(this, ZimmonActivity::class.java)
                startActivity(intent)*/
                //2차 비밀번호 다이어리 띄워줄 예정
                dialog.show()
            }
            else
            {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

        makeId.setOnClickListener {
            startActivity(Intent(this, MakeIdActivity::class.java))
        }

        dialog_pass.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(applicationContext,
                                "인증 에러: $errString", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Toast.makeText(applicationContext,
                                "인증 성공!", Toast.LENGTH_SHORT)
                                .show()

                        startActivity(Intent(applicationContext, MainActivity::class.java))

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(applicationContext, "인증 실패",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("지문 인증")
                .setSubtitle("기기에 등록된 지문을 이용항여 지문을 인증해주세요.")
                .setNegativeButtonText("취소")
                .build()

        dialog_zimoon.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun loadData(){
        val pref = getSharedPreferences("pref",0)

        MainActivity.ShareObject.useSecondPasswordBool = pref.getBoolean("useSecondPasswordBool",false)
        MainActivity.ShareObject.zimmonSwitchBool = pref.getBoolean("zimmonSwitchBool",false)
        MainActivity.ShareObject.textsecondPasswordSettingBool = pref.getBoolean("textsecondPasswordSettingBool",false)
        MainActivity.ShareObject.textsecondPasswordNumber = pref.getInt("textsecondPasswordNumber",0)
    }
}