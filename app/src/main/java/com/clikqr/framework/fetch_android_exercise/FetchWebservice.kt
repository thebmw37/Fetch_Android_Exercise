package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

class FetchWebservice {

    private val mutableData = MutableLiveData<String>()
    val liveData: LiveData<String> = mutableData

    fun requestData() {

        var response = "";
        val req = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

        runBlocking<Job> {
            GlobalScope.launch {
                response = URL(req).readText()
                mutableData.postValue(response)
            }
        }

    }

}