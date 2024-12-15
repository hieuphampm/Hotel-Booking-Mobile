package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.Locale

class SuccessActivity : AppCompatActivity() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)
        val roomType = intent.getStringExtra("ROOM_TYPE") ?: "Unknown Room"

        // Lưu thông báo vào Firestore
        saveNotificationToFirestore(totalPrice, roomType)

        findViewById<Button>(R.id.btnReturnHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun saveNotificationToFirestore(totalPrice: Double, roomType: String) {
        val notificationData = mapOf(
            "title" to "Payment Successful!",
            "description" to "You paid ${formatCurrency(totalPrice)} for $roomType.",
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("notifications")
            .add(notificationData)
            .addOnSuccessListener {
                Toast.makeText(this, "AppNotification saved", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save notification: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun formatCurrency(amount: Double): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        return currencyFormatter.format(amount)
    }
}
