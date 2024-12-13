package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class PaymentHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        // Lấy thông tin từ SharedPreferences
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val roomName = sharedPreferences.getString("room_name", "Unknown")
        val roomPrice = sharedPreferences.getString("room_price", "0.0")
        val status = sharedPreferences.getString("status", "Unknown")
        val bookingDate = sharedPreferences.getString("booking_date", "Unknown")

        val roomDetailsLayout: LinearLayout = findViewById(R.id.roomDetailsLayout)
        val totalAmountValue: TextView = findViewById(R.id.paymentHistory_totalAmountValue)


        val roomNameTextView = TextView(this)
        roomNameTextView.text = "Room Name: $roomName"

        val roomPriceTextView = TextView(this)
        roomPriceTextView.text = "Price: $roomPrice"

        val roomStatusTextView = TextView(this)
        roomStatusTextView.text = "Status: $status"


        val bookingDateTextView = TextView(this)
        bookingDateTextView.text = "Booking Date: $bookingDate"


        roomDetailsLayout.addView(roomNameTextView)
        roomDetailsLayout.addView(roomPriceTextView)
        roomDetailsLayout.addView(roomStatusTextView)
        roomDetailsLayout.addView(bookingDateTextView)


        val totalAmount = roomPrice?.toDouble() ?: 0.0
        totalAmountValue.text = "$totalAmount"
    }
}
