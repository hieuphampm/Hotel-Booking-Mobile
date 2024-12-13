package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentapp.PaymentActivity

class BookingRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_room)

        // Tìm các View và thiết lập sự kiện cho nút "Search"
        val searchButton: Button = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            // Giả sử bạn đã có các giá trị cho room name, price, status và ngày đặt phòng
            val roomName = "Luxury Room"
            val roomPrice = 200.0
            val status = "Booked"
            val bookingDate = "2024-12-15" // Ngày đặt phòng

            // Chuyển tới PaymentActivity và gửi thông tin
            val intent = Intent(this, PaymentActivity::class.java).apply {
                putExtra("room_name", roomName)
                putExtra("room_price", roomPrice)
                putExtra("status", status)
                putExtra("booking_date", bookingDate) // Thêm thông tin ngày đặt phòng
            }
            startActivity(intent)
        }
    }
}
