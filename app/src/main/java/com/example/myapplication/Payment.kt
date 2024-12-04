package com.example.hotelapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_screen_v2)

        val cardNumberEditText: EditText = findViewById(R.id.card_number)
        val cardholderNameEditText: EditText = findViewById(R.id.cardholder_name)
        val expiryDateEditText: EditText = findViewById(R.id.expiry_date)
        val cvvEditText: EditText = findViewById(R.id.cvv)
        val confirmPaymentButton: Button = findViewById(R.id.confirm_payment_button)

        confirmPaymentButton.setOnClickListener {
            val cardNumber = cardNumberEditText.text.toString()
            val cardholderName = cardholderNameEditText.text.toString()
            val expiryDate = expiryDateEditText.text.toString()
            val cvv = cvvEditText.text.toString()

            if (!validateInput(cardNumber, cardholderName, expiryDate, cvv)) {
                Toast.makeText(this, "Please check your input and try again", Toast.LENGTH_SHORT).show()
            } else {
                processPayment(cardNumber, cardholderName, expiryDate, cvv)
            }
        }
    }

    private fun validateInput(cardNumber: String, cardholderName: String, expiryDate: String, cvv: String): Boolean {
        return when {
            cardNumber.isBlank() || cardholderName.isBlank() || expiryDate.isBlank() || cvv.isBlank() -> {
                false
            }
            !isValidCardNumber(cardNumber) -> {
                false
            }
            !isValidExpiryDate(expiryDate) -> {
                false
            }
            !isValidCVV(cvv) -> {
                false
            }
            else -> true
        }
    }

    private fun isValidCardNumber(cardNumber: String): Boolean {
        return cardNumber.length in 13..19 && cardNumber.all { it.isDigit() }
    }

    private fun isValidExpiryDate(expiryDate: String): Boolean {
    
        val regex = "^\\d{2}/\\d{2}$".toRegex()
        if (!regex.matches(expiryDate)) return false

        val parts = expiryDate.split("/")
        val month = parts[0].toIntOrNull() ?: return false
        val year = parts[1].toIntOrNull() ?: return false

        return month in 1..12 && year >= 0
    }

    private fun isValidCVV(cvv: String): Boolean {
        return cvv.length in 3..4 && cvv.all { it.isDigit() }
    }

    private fun processPayment(cardNumber: String, cardholderName: String, expiryDate: String, cvv: String) {
       
        Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()

      
    }
}
