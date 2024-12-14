package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FirstPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_payment)

        // Retrieve data from the intent
        val dateInfo = intent.getStringExtra("date_info") ?: ""
        val roomDetails = intent.getStringExtra("room_details") ?: ""
        val bedInfo = intent.getStringExtra("bed_info") ?: ""
        val noSmoking = intent.getStringExtra("no_smoking") ?: ""
        val mealInfo = intent.getStringExtra("meal_info") ?: ""
        val nonRefundable = intent.getStringExtra("non_refundable") ?: ""
        val paymentInfo = intent.getStringExtra("payment_info") ?: ""
        val paymentAmount = intent.getStringExtra("payment_amount") ?: ""

        // Set the data to TextViews
        val tvDate: TextView = findViewById(R.id.tv_date)
        val tvRoomDetails: TextView = findViewById(R.id.tv_room_details)
        val tvBedInfo: TextView = findViewById(R.id.tv_bed_info)
        val tvNoSmoking: TextView = findViewById(R.id.tv_no_smoking)
        val tvMealInfo: TextView = findViewById(R.id.tv_meal_info)
        val tvNonRefundable: TextView = findViewById(R.id.tv_non_refundable)
        val tvPaymentInfo: TextView = findViewById(R.id.tv_payment_info)
        val tvPaymentAmount: TextView = findViewById(R.id.tv_payment_amount)

        tvDate.text = dateInfo
        tvRoomDetails.text = roomDetails
        tvBedInfo.text = bedInfo
        tvNoSmoking.text = noSmoking
        tvMealInfo.text = mealInfo
        tvNonRefundable.text = nonRefundable
        tvPaymentInfo.text = paymentInfo
        tvPaymentAmount.text = paymentAmount

        // Set up the book now button
        val btnBookNow: Button = findViewById(R.id.btn_book_now)
        btnBookNow.setOnClickListener {
            // Launch BookingConfirmationActivity
            val intent = Intent(this, BookingConfirmationActivity::class.java).apply {
                putExtra("room_details", roomDetails)
                putExtra("date_info", dateInfo)
                putExtra("payment_amount", paymentAmount)
            }
            startActivity(intent)
        }
    }
}

class BookingConfirmationActivity {

}
