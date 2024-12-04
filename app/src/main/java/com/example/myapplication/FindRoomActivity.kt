package com.example.hotelapp

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class FindRoomActivity : AppCompatActivity() {

    private lateinit var roomAdapter: RoomAdapter
    private lateinit var roomList: MutableList<Room>
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_room)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        searchView = findViewById(R.id.search_view)

        roomList = generateDummyRooms()

        roomAdapter = RoomAdapter(roomList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = roomAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterRooms(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterRooms(it) }
                return false
            }
        })
    }

    private fun filterRooms(query: String) {
        val filteredList = roomList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        roomAdapter.updateList(filteredList)
    }

    private fun generateDummyRooms(): MutableList<Room> {
        return mutableListOf(
            Room("Deluxe Room", "$150 per night"),
            Room("Suite Room", "$250 per night"),
            Room("Standard Room", "$100 per night"),
            Room("Family Room", "$200 per night"),
            Room("Single Room", "$80 per night")
        )
    }
}

class Room {

}
