package com.example.myapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.Room

class RoomAdapter(private val context: Context, private val roomList: List<Room>) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomType: TextView = itemView.findViewById(R.id.room_type)
        val price: TextView = itemView.findViewById(R.id.room_price)
        val description: TextView = itemView.findViewById(R.id.room_description)
        val roomImage: ImageView = itemView.findViewById(R.id.room_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.roomType.text = room.name
        holder.price.text = "$${room.price}"
        holder.description.text = room.features.joinToString(", ")

        Glide.with(context)
            .load(room.imageURL)
            .into(holder.roomImage)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
