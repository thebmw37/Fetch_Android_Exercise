package com.clikqr.framework.fetch_android_exercise

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

class FetchWebservice {

    fun requestData(): Unit {

        var response = "";
        val req = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

        return runBlocking<Unit> {
            GlobalScope.launch {
                response = URL(req).readText()
            }
        }

    }

}