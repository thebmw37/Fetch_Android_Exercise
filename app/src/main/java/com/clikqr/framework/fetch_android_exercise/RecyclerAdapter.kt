package com.clikqr.framework.fetch_android_exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val itemList: List<ListItem>): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    // Creating View Holder class with parameters for text views
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var listIdTextView: TextView = itemView.findViewById(R.id.listIdTextView)
        var idTextView: TextView = itemView.findViewById(R.id.idTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        // Inflate layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        // Setting holder parameters for the current item
        val currentItem = itemList[position]

        holder.nameTextView.text = currentItem.nameText
        holder.listIdTextView.text = currentItem.listIdText
        holder.idTextView.text = currentItem.idText
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}