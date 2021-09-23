package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainRepository {

    val fetchWebservice = FetchWebservice()

    private val mutableData = MutableLiveData<Unit>()
    val liveData: LiveData<Unit> = mutableData

    fun requestData(): Unit {
        return fetchWebservice.requestData()
    }
}