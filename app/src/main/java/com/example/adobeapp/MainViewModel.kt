package com.example.adobeapp

import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    var count = 0

    fun incrementCountVar(){
        count++
    }
}