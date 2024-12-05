package com.example.myapplication;

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ActivityFindRoomBinding
import com.example.myapplication.databinding.ActivityFindRoomBinding

class FindRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Event: Handle search bar input
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                // Perform search with 'query'
                Toast.makeText(this@FindRoomActivity, "Searching for: $query", Toast.LENGTH_SHORT).show()
            }
        })

        // Event: Handle Price filter click
        binding.filterPrice.setOnClickListener {
            Toast.makeText(this, "Filter by Price", Toast.LENGTH_SHORT).show()
        }

        // Event: Handle Availability filter click
        binding.filterAvailability.setOnClickListener {
            Toast.makeText(this, "Filter by Availability", Toast.LENGTH_SHORT).show()
        }

        // Event: Handle Features filter click
        binding.filterFeatures.setOnClickListener {
            Toast.makeText(this, "Filter by Features", Toast.LENGTH_SHORT).show()
        }

        // Initialize RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.roomListRecyclerview
        recyclerView.adapter = RoomListAdapter(getDummyRooms())
        recyclerView.setHasFixedSize(true)
    }

    private fun getDummyRooms(): List<String> {
        return listOf("Room 1", "Room 2", "Room 3", "Room 4")
    }
}
