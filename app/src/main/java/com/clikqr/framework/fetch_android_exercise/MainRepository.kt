package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainRepository {

    val fetchWebservice = FetchWebservice()

    private val mutableData = MutableLiveData<String>()
    val liveData: LiveData<String> = mutableData

    val observer = Observer<String> { webData ->
        mutableData.value = webData
    }

    init {
        fetchWebservice.liveData.observeForever(observer)
    }

    fun requestData() {
        fetchWebservice.requestData()
    }
}