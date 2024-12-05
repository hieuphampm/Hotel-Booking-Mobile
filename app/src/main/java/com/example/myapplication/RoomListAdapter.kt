package com.example.myapplication.com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Room

class RoomListAdapter(
    private val roomList: List<Room>
) : RecyclerView.Adapter<RoomListAdapter.RoomViewHolder>() {

    class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomName: TextView = view.findViewById(R.id.room_name)
        val roomType: TextView = view.findViewById(R.id.room_type)
        val roomPrice: TextView = view.findViewById(R.id.room_price)
        val roomAvailability: TextView = view.findViewById(R.id.room_availability)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_list_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]

        holder.roomName.text = room.name
        holder.roomType.text = room.type
        holder.roomPrice.text = "$${room.price}"
        holder.roomAvailability.text = if (room.isAvailable) "Available" else "Unavailable"
        holder.roomAvailability.setTextColor(
            if (room.isAvailable) holder.itemView.context.getColor(R.color.primary)
            else holder.itemView.context.getColor(R.color.primary)
        )

        holder.itemView.setOnClickListener {
            if (room.isAvailable) {
                onRoomSelected(room)
            } else {
                Toast.makeText(holder.itemView.context, "Room not available!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = roomList.size
}