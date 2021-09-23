package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()  {

    val mainRepository = MainRepository()

    private val mutableData = MutableLiveData<Unit>()
    val liveData: LiveData<Unit> = mutableData

    val observer = Observer<Unit> { webData ->
        mutableData.value = webData
    }

    init {
        mainRepository.liveData.observeForever(observer)
    }

    fun requestData() {
        mainRepository.requestData()
    }


}