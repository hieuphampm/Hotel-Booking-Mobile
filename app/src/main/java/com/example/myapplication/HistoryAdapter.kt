package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private var historyList: List<Reservation>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val reservation = historyList[position]
        holder.roomName.text = reservation.roomName  // Using roomName from Reservation class
        holder.startDate.text = reservation.startDate  // Using startDate from Reservation class
        holder.endDate.text = reservation.endDate  // Using endDate from Reservation class
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    // Update the list of reservations and notify the adapter
    fun updateList(newList: List<Reservation>) {
        historyList = newList
        notifyDataSetChanged()
    }

    // ViewHolder to hold the views for each item in the list
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.room_name)
        val startDate: TextView = itemView.findViewById(R.id.start_date)
        val endDate: TextView = itemView.findViewById(R.id.end_date)
    }
}
