package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var tvPaymentAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

  
        val etAccountNumber: EditText = findViewById(R.id.et_account_number)
        val etAccountName: EditText = findViewById(R.id.et_account_name)
        val etBankName: EditText = findViewById(R.id.et_bank_name)
        val btnConfirmPayment: Button = findViewById(R.id.btn_confirm_payment)
        tvPaymentAmount = findViewById(R.id.tv_payment_amount)

        val paymentAmount = intent.getStringExtra("room_price") ?: "0"

        
        tvPaymentAmount.text = "Amount: $paymentAmount"

        btnConfirmPayment.setOnClickListener {
            val accountNumber = etAccountNumber.text.toString().trim()
            val accountName = etAccountName.text.toString().trim()
            val bankName = etBankName.text.toString().trim()

            if (accountNumber.isEmpty() || accountName.isEmpty() || bankName.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
