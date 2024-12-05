package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchListActivity : AppCompatActivity() {

    private lateinit var roomSearchAdapter: RoomSearchAdapter
    private lateinit var roomList: MutableList<Room>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_list_screen)

        val recyclerView: RecyclerView = findViewById(R.id.room_recycler_view)
        searchEditText = findViewById(R.id.search_edit_text)

        roomList = generateDummyRooms()

        roomSearchAdapter = RoomSearchAdapter(roomList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = roomSearchAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().toLowerCase().trim()
                if (query.isNotEmpty()) {
                    val filteredList = roomList.filter {
                        it.roomName.toLowerCase().contains(query) ||
                                it.roomType.toLowerCase().contains(query) ||
                                it.price.toString().contains(query)
                    }
                    roomSearchAdapter.updateList(filteredList)
                } else {
                    roomSearchAdapter.updateList(roomList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun generateDummyRooms(): MutableList<Room> {
        return mutableListOf(
            Room("Deluxe Room", "Luxury", 200),
            Room("Standard Room", "Basic", 100),
            Room("Suite Room", "Luxury", 300),
            Room("Single Room", "Basic", 80),
            Room("Family Room", "Premium", 150)
        )
    }
}
