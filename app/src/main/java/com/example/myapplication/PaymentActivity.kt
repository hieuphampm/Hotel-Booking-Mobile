package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

     
        val etAccountNumber: EditText = findViewById(R.id.card_number)
        val etAccountName: EditText = findViewById(R.id.cardholder_name)
        val etBankName: EditText = findViewById(R.id.payment_method_spinner)
        val etPaymentAmount: EditText = findViewById(R.id.payment_amount)
        val btnConfirmPayment: Button = findViewById(R.id.confirm_payment_button)

     
        val paymentAmount = intent.getStringExtra("payment_amount") ?: "0"
        etPaymentAmount.setText(paymentAmount)

        btnConfirmPayment.setOnClickListener {
            val accountNumber = etAccountNumber.text.toString().trim()
            val accountName = etAccountName.text.toString().trim()
            val bankName = etBankName.text.toString().trim()

            if (accountNumber.isEmpty() || accountName.isEmpty() || bankName.isEmpty()) {
                Toast.makeText(this, "Please complete the information", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show()
         
            }
        }
    }
}
