package com.example.adobeapp

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var TAG = MainViewModel::class.java.simpleName

    var _seconds = MutableLiveData<Int>()
    //_ meanns mutable
    lateinit var timer: CountDownTimer

    var count = 0

    fun incrementCountVar(){
        count++
    }


    fun startTimer(){
        timer = object :CountDownTimer(10_000,1_000){
            override fun onTick(timeRemainning: Long) {
                _seconds.value = timeRemainning.toInt()
                Log.i(TAG,"seconds value ="+_seconds)
            }
            override fun onFinish() {
                Log.i(TAG,"timer finnished")
            }
        }.start()
    }
}