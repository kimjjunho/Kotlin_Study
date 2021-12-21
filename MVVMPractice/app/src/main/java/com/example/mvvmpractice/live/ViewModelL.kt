package com.example.mvvmpractice.live

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelL : ViewModel(){
    private val TAG = "ViewModelL"

    //뮤터블 라이브 테이터 - 수정 가능
    //라이브 데이터 - 값 변동 안됨

    //내부에서 설정하는 자료형은 뮤터블로 변경가능하도록 설정
    private val _currenValue = MutableLiveData<Int>()

    val currentValue : LiveData<Int>
        get() = _currenValue

    //초기값 설정
    init {
        Log.d(TAG, "- 생성자 호출")
        _currenValue.value = 0
    }

    fun updateValue(actionType: ActionType, input:Int){
        when(actionType){
            ActionType.PLUS->_currenValue.value = _currenValue.value?.plus(input)
            ActionType.MINUS->_currenValue.value = _currenValue.value?.minus(input)
        }
    }
}
enum class ActionType{
    PLUS, MINUS
}