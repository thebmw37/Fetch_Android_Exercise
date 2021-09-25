package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

class FetchWebservice {

    // Live data variables
    private val mutableData = MutableLiveData<String>()
    val liveData: LiveData<String> = mutableData

    // Function requests data from the fetch
    fun requestData() {

        var response = "";
        val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

        // Launch coroutine for fetching data from the web
        runBlocking<Job> {
            GlobalScope.launch {
                response = URL(url).readText()
                mutableData.postValue(response)
            }
        }

    }

}