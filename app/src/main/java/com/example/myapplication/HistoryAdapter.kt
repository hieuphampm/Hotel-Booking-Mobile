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
        holder.roomName.text = reservation.roomName
        holder.startDate.text = reservation.startDate
        holder.endDate.text = reservation.endDate
        holder.guestName.text = reservation.guestName
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateList(newList: List<Reservation>) {
        historyList = newList
        notifyDataSetChanged()
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.room_name)
        val startDate: TextView = itemView.findViewById(R.id.start_date)
        val endDate: TextView = itemView.findViewById(R.id.end_date)
        val guestName: TextView = itemView.findViewById(R.id.guest_name)
    }
}
