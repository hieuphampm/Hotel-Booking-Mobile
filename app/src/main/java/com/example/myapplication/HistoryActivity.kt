package com.example.hotelapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HistoryActivity : AppCompatActivity() {

    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var bookingHistoryList: MutableList<Reservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_screen)

        val recyclerView: RecyclerView = findViewById(R.id.main)

        bookingHistoryList = generateDummyBookingHistory()

        historyAdapter = HistoryAdapter(bookingHistoryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = historyAdapter
    }

    private fun generateDummyBookingHistory(): MutableList<Reservation> {
        return mutableListOf(
            Reservation("Deluxe Room", "12/12/2023", "15/12/2023", "John Doe"),
            Reservation("Standard Room", "01/11/2023", "05/11/2023", "Jane Smith"),
            Reservation("Suite Room", "10/10/2023", "12/10/2023", "Alice Johnson")
        )
    }

    class Reservation(s: String, s1: String, s2: String, s3: String) {

    }
}
