package com.example.axxessassignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.axxessassignment.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val viewModel=ViewModelProvider(this).get(TestViewModel::class.java)


        txtValue.text=viewModel.number.toString()

        btnClick.setOnClickListener {

            viewModel.counterIncrement()
            txtValue.text=viewModel.number.toString()
        }


    }
}