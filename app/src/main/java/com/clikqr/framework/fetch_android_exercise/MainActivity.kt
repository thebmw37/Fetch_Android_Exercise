package com.clikqr.framework.fetch_android_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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
        val observer = Observer<List<ListItem>> { sortedWebData ->

            // Hiding progress bar
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE

            // Setting up Recycler View
            findViewById<RecyclerView>(R.id.recycler_view).adapter = RecyclerAdapter(sortedWebData)
            findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(this)
            findViewById<RecyclerView>(R.id.recycler_view).setHasFixedSize(true)

        }

        // Set observer for View Model
        viewModel.liveData.observeForever(observer)

        // Request data through View Model
        viewModel.requestData()

    }
}