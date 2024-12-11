package com.example.hotelapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val headerTextView: TextView = findViewById(R.id.tv_header)
        val successMessageTextView: TextView = findViewById(R.id.tv_success_message)
        val bookingDetailsTextView: TextView = findViewById(R.id.tv_booking_details)
        val roomDetailsTextView: TextView = findViewById(R.id.tv_room_details)
        val bedInfoTextView: TextView = findViewById(R.id.tv_bed_info)
        val noSmokingTextView: TextView = findViewById(R.id.tv_no_smoking)
        val mealInfoTextView: TextView = findViewById(R.id.tv_meal_info)
        val nonRefundableTextView: TextView = findViewById(R.id.tv_non_refundable)
        val closeButton: Button = findViewById(R.id.btn_close)

        headerTextView.text = getString(R.string.payment_success)
        successMessageTextView.text = getString(R.string.payment_success_message)

        bookingDetailsTextView.text = "Date: 12/12/2024"
        roomDetailsTextView.text = "Room: Deluxe Suite"
        bedInfoTextView.text = "Bed: King Size"
        noSmokingTextView.text = "No Smoking"
        mealInfoTextView.text = "Meal: Included"
        nonRefundableTextView.text = "Non-Refundable: Yes"

        closeButton.setOnClickListener {
            finish()
        }
    }
}
