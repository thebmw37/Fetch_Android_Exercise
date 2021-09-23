package com.clikqr.framework.fetch_android_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the View Model
        viewModel = ViewModelProvider(this).get(MainViewModel()::class.java)

        val mutableData = MutableLiveData<Unit>()

        val observer = Observer<Unit> { webData ->
            mutableData.value = webData

            println(mutableData.value)
        }

        viewModel.liveData.observeForever(observer)

        viewModel.requestData()

    }
}