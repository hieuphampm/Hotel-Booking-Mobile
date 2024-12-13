package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Room
import com.example.myapplication.RoomAdapter
import com.example.myapplication.RoomDetailsActivity
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var roomsRecyclerView: RecyclerView
    private lateinit var roomsAdapter: RoomAdapter
    private val roomList = mutableListOf<Room>()
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        roomsRecyclerView = view.findViewById(R.id.roomsRecyclerView)
        roomsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Khởi tạo RoomAdapter với sự kiện click
        roomsAdapter = RoomAdapter(requireContext(), roomList, object : RoomAdapter.OnRoomClickListener {
            override fun onRoomClick(room: Room) {
                // Mở RoomDetailsActivity và truyền thông tin phòng
                val intent = Intent(requireContext(), RoomDetailsActivity::class.java)
                intent.putExtra("ROOM_ID", room.id)
                startActivity(intent)
            }
        })

        roomsRecyclerView.adapter = roomsAdapter

        loadRooms()

        return view
    }

    private fun loadRooms() {
        firestore.collection("rooms")
            .get()
            .addOnSuccessListener { querySnapshot ->
                roomList.clear()
                for (doc in querySnapshot) {
                    val room = doc.toObject(Room::class.java)
                    room.id = doc.id
                    roomList.add(room)
                }
                roomsAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to load data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
