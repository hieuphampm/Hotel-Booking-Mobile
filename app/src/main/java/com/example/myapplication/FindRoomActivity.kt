package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FindRoomActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var filterPriceButton: Button
    private lateinit var filterAvailabilityButton: Button
    private lateinit var filterFeaturesButton: Button
    private lateinit var roomListRecyclerView: RecyclerView

    private lateinit var roomAdapter: RoomAdapter
    private val rooms = mutableListOf<Room>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_room)

        // Initialize UI components
        searchBar = findViewById(R.id.search_bar)
        filterPriceButton = findViewById(R.id.filter_price)
        filterAvailabilityButton = findViewById(R.id.filter_availability)
        filterFeaturesButton = findViewById(R.id.filter_features)
        roomListRecyclerView = findViewById(R.id.room_list_recyclerview)

        // Setup RecyclerView and Adapter
        roomListRecyclerView.layoutManager = LinearLayoutManager(this)
        roomAdapter = RoomAdapter()
        roomListRecyclerView.adapter = roomAdapter

        // Load dummy data
        rooms.addAll(getDummyRooms())
        roomAdapter.updateRoomList(rooms)

        // Search bar listener
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterRooms(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Filter button listeners
        filterPriceButton.setOnClickListener {
            filterRooms("price")
        }

        filterAvailabilityButton.setOnClickListener {
            filterRooms("availability")
        }

        filterFeaturesButton.setOnClickListener {
            filterRooms("features")
        }
    }

    private fun filterRooms(query: String) {
        val filteredRooms = when {
            query.equals("price", ignoreCase = true) -> {
                rooms.sortedBy { it.price }
            }
            query.equals("availability", ignoreCase = true) -> {
                rooms.filter { it.isAvailable }
            }
            query.equals("features", ignoreCase = true) -> {
                rooms.filter { "Wi-Fi" in it.features }
            }
            else -> {
                rooms.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
        roomAdapter.submitList(filteredRooms)
    }

    private fun getDummyRooms(): List<Room> {
        return listOf(
            Room("Room 1", 100.0, true, listOf("Wi-Fi", "Air Conditioning")),
            Room("Room 2", 150.0, false, listOf("Wi-Fi", "Pool")),
            Room("Room 3", 120.0, true, listOf("Wi-Fi", "TV")),
            Room("Room 4", 200.0, true, listOf("Wi-Fi", "Gym"))
        )
    }
}
