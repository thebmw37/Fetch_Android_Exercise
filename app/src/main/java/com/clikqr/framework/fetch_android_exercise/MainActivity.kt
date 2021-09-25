package com.clikqr.framework.fetch_android_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the View Model
        viewModel = ViewModelProvider(this).get(MainViewModel()::class.java)

        // Create mutable data object for request data
        val mutableData = MutableLiveData<String>()

        // Variable for formatting data in JSON
        var jsonData: JSONArray

        // Final List for sorted data
        var listData: List<ListItem> = listOf()

        // Observer for obtaining live data from View Model
        val observer = Observer<String> { webData ->

            mutableData.value = webData

            println(mutableData.value)

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
            val sortedData = listOf(ListItem("id", "listId", "name")) +
                    listData.sortedWith(compareBy({ it.listIdText }, { it.nameText.toInt() }))

            // Setting up Recycler View
            findViewById<RecyclerView>(R.id.recycler_view).adapter = RecyclerAdapter(sortedData)
            findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(this)
            findViewById<RecyclerView>(R.id.recycler_view).setHasFixedSize(true)

        }

        // Set observer for View Model
        viewModel.liveData.observeForever(observer)

        // Request data through View Model
        viewModel.requestData()

    }
}