package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookingConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        // Retrieve data from the intent
        val roomDetails = intent.getStringExtra("room_details") ?: ""
        val dateInfo = intent.getStringExtra("date_info") ?: ""
        val paymentAmount = intent.getStringExtra("payment_amount") ?: ""

        // Set the data to TextViews
        val tvRoomDetails: TextView = findViewById(R.id.tv_room_details)
        val tvDateInfo: TextView = findViewById(R.id.tv_date_info)
        val tvPaymentAmount: TextView = findViewById(R.id.tv_payment_amount)

        tvRoomDetails.text = roomDetails
        tvDateInfo.text = dateInfo
        tvPaymentAmount.text = paymentAmount
    }
}
