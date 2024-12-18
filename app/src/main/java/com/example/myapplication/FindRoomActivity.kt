package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RoomAdapter

class FindRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
