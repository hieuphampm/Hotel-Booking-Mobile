package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
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

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Payments")
        firestore = FirebaseFirestore.getInstance()

        cardNumber = findViewById(R.id.card_number)
        cardholderName = findViewById(R.id.cardholder_name)
        expiryDate = findViewById(R.id.expiry_date)
        cvv = findViewById(R.id.cvv)
        paymentMethodSpinner = findViewById(R.id.payment_method_spinner)
        confirmPaymentButton = findViewById(R.id.confirm_payment_button)

        paymentMethodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMethod = parent?.getItemAtPosition(position).toString()
                if (selectedMethod == "Cash") {
                    toggleCardFieldsVisibility(false)
                } else {
                    toggleCardFieldsVisibility(true)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        confirmPaymentButton.setOnClickListener {
            savePaymentData()
        }
    }

    private fun toggleCardFieldsVisibility(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        cardNumber.visibility = visibility
        cardholderName.visibility = visibility
        expiryDate.visibility = visibility
        cvv.visibility = visibility
    }

    private fun savePaymentData() {
        val paymentMethod = paymentMethodSpinner.selectedItem.toString()

        if (paymentMethod == "Cash") {
            val paymentData = hashMapOf(
                "PaymentMethod" to paymentMethod
            )

            firestore.collection("Payments").add(paymentData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Payment saved successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to save payment", Toast.LENGTH_SHORT).show()
                    Log.e("Firestore", "Error saving payment", e)
                }
            return
        }

        val cardNum = cardNumber.text.toString().trim()
        val holderName = cardholderName.text.toString().trim()
        val expDate = expiryDate.text.toString().trim()
        val cardCVV = cvv.text.toString().trim()

        if (cardNum.isEmpty() || holderName.isEmpty() || expDate.isEmpty() || cardCVV.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val paymentData = hashMapOf(
            "CardNumber" to cardNum,
            "CardholderName" to holderName,
            "ExpiryDate" to expDate,
            "CVV" to cardCVV,
            "PaymentMethod" to paymentMethod
        )

        firestore.collection("Payments").add(paymentData)
            .addOnSuccessListener {
                Toast.makeText(this, "Payment saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save payment", Toast.LENGTH_SHORT).show()
                Log.e("Firestore", "Error saving payment", e)
            }
    }
}
