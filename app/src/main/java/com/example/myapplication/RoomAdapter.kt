package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    private var roomList = mutableListOf<Room>()

    // ViewHolder for each room item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    // Binding data to the ViewHolder
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.roomName.text = room.name
        holder.roomPrice.text = room.price.toString()
        holder.roomAvailability.text = room.availability
        holder.roomFeatures.text = room.features
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    // Update the room list with new data
    fun updateRoomList(newRoomList: List<Room>) {
        roomList.clear()
        roomList.addAll(newRoomList)
        notifyDataSetChanged()
    }

    // ViewHolder to bind views from item_room.xml
    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.room_name)
        val roomPrice: TextView = itemView.findViewById(R.id.room_price)
        val roomAvailability: TextView = itemView.findViewById(R.id.room_availability)
        val roomFeatures: TextView = itemView.findViewById(R.id.room_features)
    }
}
