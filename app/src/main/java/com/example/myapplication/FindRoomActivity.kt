package com.example.myapplication

import android.content.Intent
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
import com.example.paymentapp.PaymentHistoryActivity

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


        val bookButton: Button = findViewById(R.id.book_button)
        bookButton.setOnClickListener {

            val intent = Intent(this, PaymentHistoryActivity::class.java)


            val roomName = "Room A"
            val roomPrice = 150.0
            val status = "Success"

            intent.putExtra("room_name", roomName)
            intent.putExtra("room_price", roomPrice)
            intent.putExtra("status", status)


            startActivity(intent)
        }
    }
}


