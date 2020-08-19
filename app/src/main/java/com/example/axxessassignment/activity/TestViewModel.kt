package com.example.axxessassignment.activity

import androidx.lifecycle.ViewModel

class TestViewModel: ViewModel() {

    var number=0;

    fun counterIncrement(){
        number++;
    }

    override fun onCleared() {
        super.onCleared()

    }
}