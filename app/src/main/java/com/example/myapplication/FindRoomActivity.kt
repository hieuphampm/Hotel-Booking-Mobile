package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RoomAdapter
import com.example.myapplication.Room

class FindRoomActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var filterPriceButton: Button
    private lateinit var filterAvailabilityButton: Button
    private lateinit var filterFeaturesButton: Button
    private lateinit var roomListRecyclerView: RecyclerView

    private lateinit var roomAdapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_room)

        searchBar = findViewById(R.id.search_bar)
        filterPriceButton = findViewById(R.id.filter_price)
        filterAvailabilityButton = findViewById(R.id.filter_availability)
        filterFeaturesButton = findViewById(R.id.filter_features)
        roomListRecyclerView = findViewById(R.id.room_list_recyclerview)

        roomListRecyclerView.layoutManager = LinearLayoutManager(this)
        roomAdapter = RoomAdapter()
        roomListRecyclerView.adapter = roomAdapter

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterRooms(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

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
    }

}
