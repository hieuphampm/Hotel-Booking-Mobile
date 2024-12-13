package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class PaymentActivity : AppCompatActivity() {

    private lateinit var cardNumber: EditText
    private lateinit var cardholderName: EditText
    private lateinit var expiryDate: EditText
    private lateinit var cvv: EditText
    private lateinit var paymentMethodSpinner: Spinner
    private lateinit var confirmPaymentButton: Button

    // Firebase
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Payments")
        firestore = FirebaseFirestore.getInstance()

        // Initialize UI elements
        cardNumber = findViewById(R.id.card_number)
        cardholderName = findViewById(R.id.cardholder_name)
        expiryDate = findViewById(R.id.expiry_date)
        cvv = findViewById(R.id.cvv)
        paymentMethodSpinner = findViewById(R.id.payment_method_spinner)
        confirmPaymentButton = findViewById(R.id.confirm_payment_button)

        confirmPaymentButton.setOnClickListener {
            savePaymentData()
        }
    }

    private fun savePaymentData() {
        val cardNum = cardNumber.text.toString().trim()
        val holderName = cardholderName.text.toString().trim()
        val expDate = expiryDate.text.toString().trim()
        val cardCVV = cvv.text.toString().trim()
        val paymentMethod = paymentMethodSpinner.selectedItem.toString()

        if (cardNum.isEmpty() || holderName.isEmpty() || expDate.isEmpty() || cardCVV.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a map to store payment data
        val paymentData = hashMapOf(
            "CardNumber" to cardNum,
            "CardholderName" to holderName,
            "ExpiryDate" to expDate,
            "CVV" to cardCVV,
            "PaymentMethod" to paymentMethod
        )

        // Save to Realtime Database
        databaseReference.push().setValue(paymentData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Payment saved successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to save payment", Toast.LENGTH_SHORT).show()
                }
            }

        // Save to Firestore (Optional)
        firestore.collection("Payments").add(paymentData)
            .addOnSuccessListener {
                Toast.makeText(this, "Firestore: Payment saved!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Firestore: Failed to save payment", Toast.LENGTH_SHORT).show()
            }
    }
}