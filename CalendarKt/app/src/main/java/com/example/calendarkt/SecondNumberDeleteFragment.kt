package com.example.calendarkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second_number_delete.*
import kotlinx.android.synthetic.main.fragment_second_number_setting.*

class SecondNumberDeleteFragment : Fragment(){

    var count : Int = 0
    var a : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview : View =  inflater.inflate(R.layout.fragment_second_number_delete, container, false)


        val delBtn1 : Button = rootview.findViewById(R.id.delBtn1)
        val delBtn2 : Button = rootview.findViewById(R.id.delBtn2)
        val delBtn3 : Button = rootview.findViewById(R.id.delBtn3)
        val delBtn4 : Button = rootview.findViewById(R.id.delBtn4)
        val delBtn5 : Button = rootview.findViewById(R.id.delBtn5)
        val delBtn6 : Button = rootview.findViewById(R.id.delBtn6)
        val delBtn7 : Button = rootview.findViewById(R.id.delBtn7)
        val delBtn8 : Button = rootview.findViewById(R.id.delBtn8)
        val delBtn9 : Button = rootview.findViewById(R.id.delBtn9)
        val delBtn0 : Button = rootview.findViewById(R.id.delBtn0)
        val delCheackText : TextView = rootview.findViewById(R.id.delCheackText)
        val delOneDel : ImageButton = rootview.findViewById(R.id.delOneDel)
        val delCheackBtn : Button = rootview.findViewById(R.id.delCheackBtn)
        val delMessage : TextView = rootview.findViewById(R.id.delMessage)

        delBtn1.setOnClickListener {
            btnClick(1)
        }
        delBtn2.setOnClickListener {
            btnClick(2)
        }
        delBtn3.setOnClickListener {
            btnClick(3)
        }
        delBtn4.setOnClickListener {
            btnClick(4)
        }
        delBtn5.setOnClickListener {
            btnClick(5)
        }
        delBtn6.setOnClickListener {
            btnClick(6)
        }
        delBtn7.setOnClickListener {
            btnClick(7)
        }
        delBtn8.setOnClickListener {
            btnClick(8)
        }
        delBtn9.setOnClickListener {
            btnClick(9)
        }
        delBtn0.setOnClickListener {
            if(count == 0)
            {
                Toast.makeText(activity,"처음 숫자로 0이 올 수 없습니다",Toast.LENGTH_SHORT).show()
            }
            else
            {
                btnClick(0)
            }
        }

        delOneDel.setOnClickListener {
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
                    delCheackText.text = null
                }
                else
                {
                    delCheackText.text = intTostring
                }
                if(count<0)
                {
                    count = 0
                    a = 0
                }
            }



            delCheackBtn.setOnClickListener {
                if(MainActivity.ShareObject.textsecondPasswordSettingBool){

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
            delCheackText.text = intTostring
        }
    }
}