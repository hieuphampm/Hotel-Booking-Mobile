package com.example.hotelapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RoomDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_detail)

        val roomImage: ImageView = findViewById(R.id.room_image)
        val roomName: TextView = findViewById(R.id.room_name)
        val roomPrice: TextView = findViewById(R.id.room_price)
        val roomFeatures: TextView = findViewById(R.id.room_features)
        val bookNowButton: Button = findViewById(R.id.book_now_button)

        roomImage.setImageResource(R.drawable.sample_room_image)
        roomName.text = "Deluxe Room"
        roomPrice.text = "$150 per night"
        roomFeatures.text = "• Free Wi-Fi\n• Air Conditioning\n• Ocean View\n• Breakfast Included"

        bookNowButton.setOnClickListener {
            Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show()
        }
    }
}
