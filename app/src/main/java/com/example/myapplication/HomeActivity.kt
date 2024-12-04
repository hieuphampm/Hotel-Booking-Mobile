package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelapp.FindRoomActivity
import com.example.hotelapp.HistoryActivity
import com.example.hotelapp.RoomDetailActivity
import com.example.hotelapp.ReservationActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val btnFindRoom = findViewById<Button>(R.id.btnFindRoom)
        btnFindRoom.setOnClickListener {
            val intent = Intent(this, FindRoomActivity::class.java)
            startActivity(intent)
        }

        val btnReservations = findViewById<Button>(R.id.btnReservations)
        btnReservations.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }

        val btnRoomDetails = findViewById<Button>(R.id.btnRoomDetails)
        btnRoomDetails.setOnClickListener {
            val intent = Intent(this, RoomDetailActivity::class.java)
            startActivity(intent)
        }

        val btnHistory = findViewById<Button>(R.id.btnHistory)
        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}