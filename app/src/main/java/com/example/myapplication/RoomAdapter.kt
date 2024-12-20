package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class RoomAdapter(
    private val context: Context,
    private val roomList: List<Room>,
    private val onRoomClickListener: OnRoomClickListener
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    interface OnRoomClickListener {
        fun onRoomClick(room: Room)
    }

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomType: TextView = itemView.findViewById(R.id.room_type)
        val price: TextView = itemView.findViewById(R.id.room_price)
        val description: TextView = itemView.findViewById(R.id.room_description)
        val roomImage: ImageView = itemView.findViewById(R.id.room_image)

        fun bind(room: Room) {
            roomType.text = room.roomType

            val priceInVND = room.price
            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(priceInVND)
            price.text = formattedPrice

            description.text = room.description

            Glide.with(context)
                .load(room.imageURL)
                .into(roomImage)

            itemView.setOnClickListener {
                onRoomClickListener.onRoomClick(room)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.bind(room)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}