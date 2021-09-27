package com.clikqr.framework.fetch_android_exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class MainRepository {

    val fetchWebservice = FetchWebservice()

    // Live data variables
    private val mutableData = MutableLiveData<List<ListItem>>()
    val liveData: LiveData<List<ListItem>> = mutableData

    // Variable for formatting data in JSON
    lateinit var jsonData: JSONArray

    // Final List for sorted data
    var listData: List<ListItem> = listOf()

    // Setting observer to update mutableData
    val observer = Observer<String> { webData ->

        println(webData)

        jsonData = JSONArray(webData)

        // Iterate through the data and create ListItems and append them to listData
        for (i in 0 until jsonData.length()) {

            // Convert data into JSON Object
            val rowData = jsonData.getJSONObject(i)

            if(!rowData["name"].equals(null) && !rowData["name"].equals("")) {

                val newItem = ListItem(rowData["id"].toString(), rowData["listId"].toString(), rowData["name"] as String)

                listData += newItem

            }
        }

        // Sorting list data
        // Sorting by name gives out of order numeric results, so we convert nameText to int to sort properly
        val sortedData = listData.sortedWith(compareBy({ it.listIdText }, { it.nameText.toInt() }))

        mutableData.value = sortedData

    }

    // Initialize observer
    init {
        fetchWebservice.liveData.observeForever(observer)
    }

    // Request data from repository
    fun requestData() {
        fetchWebservice.requestData()
    }
}