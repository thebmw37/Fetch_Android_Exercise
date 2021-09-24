package com.clikqr.framework.fetch_android_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the View Model
        viewModel = ViewModelProvider(this).get(MainViewModel()::class.java)

        val mutableData = MutableLiveData<String>()

        var fetchData: JSONArray

        var listData: List<ListItem> = listOf()

        val observer = Observer<String> { webData ->
            mutableData.value = webData

            println(mutableData.value)

            fetchData = JSONArray(webData)

            for (i in 0 until fetchData.length()) {
                val rowData = fetchData.getJSONObject(i)

                if(!rowData["name"].equals(null) && !rowData["name"].equals("")) {

                    val newItem = ListItem(rowData["id"].toString(), rowData["listId"].toString(), rowData["name"] as String)

                    listData += newItem

                }
            }

            // Sorting simply by name gives out of order numeric results in the list

            val sortedData = listOf(ListItem("id", "listId", "name")) + listData.sortedWith(compareBy({ it.listIdText }, { it.nameText.toInt() }))

            // it.nameText.slice(4 until it.nameText.length - 1).toInt()

            findViewById<RecyclerView>(R.id.recycler_view).adapter = RecyclerAdapter(sortedData)
            findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(this)
            findViewById<RecyclerView>(R.id.recycler_view).setHasFixedSize(true)

        }

        viewModel.liveData.observeForever(observer)

        viewModel.requestData()

    }
}