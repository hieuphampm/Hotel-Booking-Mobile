package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class RoomDetailsActivity : AppCompatActivity() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)

        val roomId = intent.getStringExtra("ROOM_ID")
        if (roomId != null) {
            loadRoomDetails(roomId)
        }

        findViewById<Button>(R.id.btnBookRoom).setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("ROOM_ID", roomId)
            startActivity(intent)
        }
    }

    private fun loadRoomDetails(roomId: String) {
        firestore.collection("rooms").document(roomId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val room = documentSnapshot.toObject(Room::class.java)
                if (room != null) {
                    findViewById<TextView>(R.id.tvRoomName).text = room.roomType
                    findViewById<TextView>(R.id.tvRoomPrice).text = room.price.toString()
                    findViewById<TextView>(R.id.tvRoomDescription).text = room.description
                    findViewById<TextView>(R.id.tvRoomAmenities).text = room.amenities.joinToString(", ")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load room details: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
