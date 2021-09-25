package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()  {

    val mainRepository = MainRepository()

    // Live data variables
    private val mutableData = MutableLiveData<String>()
    val liveData: LiveData<String> = mutableData

    // Setting observer to update mutableData
    val observer = Observer<String> { webData ->
        mutableData.value = webData
    }

    // Initialize observer
    init {
        mainRepository.liveData.observeForever(observer)
    }

    // Request data from repository
    fun requestData() {
        mainRepository.requestData()
    }


}