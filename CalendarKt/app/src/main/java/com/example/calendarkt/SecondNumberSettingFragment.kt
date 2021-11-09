package com.example.calendarkt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_second_number_setting.*


class SecondNumberSettingFragment : Fragment() {


    var a : Int = 0
    var cheack : Boolean = false
    var samePass : Boolean = false
    var cheackNum : Int = 0
    var count : Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview : View = inflater.inflate(R.layout.fragment_second_number_setting, container, false)

        val settingBtn1 : Button = rootview.findViewById(R.id.settingBtn1)
        val settingBtn2 : Button = rootview.findViewById(R.id.settingBtn2)
        val settingBtn3 : Button = rootview.findViewById(R.id.settingBtn3)
        val settingBtn4 : Button = rootview.findViewById(R.id.settingBtn4)
        val settingBtn5 : Button = rootview.findViewById(R.id.settingBtn5)
        val settingBtn6 : Button = rootview.findViewById(R.id.settingBtn6)
        val settingBtn7 : Button = rootview.findViewById(R.id.settingBtn7)
        val settingBtn8 : Button = rootview.findViewById(R.id.settingBtn8)
        val settingBtn9 : Button = rootview.findViewById(R.id.settingBtn9)
        val settingBtn0 : Button = rootview.findViewById(R.id.settingBtn0)
        val settingCheackText : TextView = rootview.findViewById(R.id.settingCheackText)
        val settingOneDel : ImageButton = rootview.findViewById(R.id.settingOneDel)
        val settingCheackBtn : Button = rootview.findViewById(R.id.settingCheackBtn)
        val settingMessage : TextView = rootview.findViewById(R.id.settingMessage)


        settingBtn1.setOnClickListener {
            btnClick(1)
        }
        settingBtn2.setOnClickListener {
          btnClick(2)
        }
        settingBtn3.setOnClickListener {
            btnClick(3)
        }
        settingBtn4.setOnClickListener {
            btnClick(4)
        }
        settingBtn5.setOnClickListener {
            btnClick(5)
        }
        settingBtn6.setOnClickListener {
            btnClick(6)
        }
        settingBtn7.setOnClickListener {
            btnClick(7)
        }
        settingBtn8.setOnClickListener {
            btnClick(8)
        }
        settingBtn9.setOnClickListener {
            btnClick(9)
        }
        settingBtn0.setOnClickListener {
            if(count == 0)
            {
                Toast.makeText(activity,"처음 숫자로 0이 올 수 없습니다",Toast.LENGTH_SHORT).show()
            }
            else
            {
                btnClick(0)
            }
        }

        if(MainActivity.ShareObject.textsecondPasswordSettingBool){
            settingMessage.text = "기존 비밀번호를 입력하시오"
        }
        else{
            settingMessage.text = "설정하실 비밀번호를 입력하시오"
        }

        settingOneDel.setOnClickListener {
            if(count == 0)
            {
                Toast.makeText(activity,"삭제할 문자가 존재하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else
            {
                count--
                a /= 10
                val intTostring : String = a.toString()
                if(a == 0)
                {
                    settingCheackText.text = null
                }
                else
                {
                    settingCheackText.text = intTostring
                }
                if(count<0)
                {
                    count = 0
                    a = 0
                }
            }
        }

        settingCheackBtn.setOnClickListener {
            if (MainActivity.ShareObject.textsecondPasswordSettingBool) {
                if (samePass) {
                    if (cheackNum == a) {
                        MainActivity.ShareObject.textsecondPasswordNumber = a
                        MainActivity.ShareObject.textsecondPasswordSettingBool = true
                        Toast.makeText(activity, "비밀번호 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        cheack = false
                        samePass = false
                        settingMessage.text = "설정 홈에서 저장버튼을 꼭 눌러주세요"
                        a = 0
                        count = 0
                        settingCheackText.text = ""
                    } else {
                        Toast.makeText(activity, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                        settingCheackText.text = ""
                        count = 0
                        a = 0
                    }
                } else if (cheack) {
                    if (a >= 10000 || a < 1111) {
                        Toast.makeText(activity, "비밀번호는 4자리 숫자로 만들어야 합니다", Toast.LENGTH_SHORT).show()
                        a = 0
                        count = 0
                    } else {
                        settingMessage.text = "비밀번호 확인하기"
                        cheackNum = a
                        a = 0
                        count = 0
                        samePass = true
                    }
                } else if (MainActivity.ShareObject.textsecondPasswordNumber == a) {
                    settingMessage.text = "변경할 비밀번호를 입력하시오"
                    a = 0
                    count = 0
                    cheack = true
                } else if (MainActivity.ShareObject.textsecondPasswordNumber != a) {
                    settingCheackText.text = null
                    a = 0
                    count = 0
                    Toast.makeText(activity, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                }
            } else if (!MainActivity.ShareObject.textsecondPasswordSettingBool) {
                if (cheack) {
                    if (cheackNum == a) {
                        MainActivity.ShareObject.textsecondPasswordNumber = a
                        MainActivity.ShareObject.textsecondPasswordSettingBool = true
                        cheack = false
                        settingMessage.text = "설정 홈에서 저장버튼을 꼭 눌러주세요"
                        settingCheackText.text = ""
                        Toast.makeText(activity, "비밀번호 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    }
                } else if (a >= 10000 || a < 1111) {
                    Toast.makeText(activity, "비밀번호는 4글자로 설정이 가능합니다", Toast.LENGTH_SHORT).show()
                    a = 0
                    count = 0
                    settingCheackText.text = ""
                } else {
                    cheack = true
                    settingMessage.text = "비밀번호 확인하기"
                    settingCheackText.text = ""
                    count = 0
                    cheackNum = a
                    a = 0
                }
            }
        }

        return rootview
    }


    private fun btnClick(num:Int)
    {
        if(count>=4)
        {
            Toast.makeText(activity,"비밀번호는 4자리로 설정가능합니다", Toast.LENGTH_SHORT).show()
        }
        else
        {
            count++
            a = a*10+num
            val intTostring : String = a.toString()
            settingCheackText.text = intTostring
        }
    }

}