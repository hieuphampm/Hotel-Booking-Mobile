package com.example.paymentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        val paymentAmount = intent.getStringExtra("payment_amount") ?: "0.00"


        val tvPaymentAmount: TextView = findViewById(R.id.tvPaymentAmount)
        val etCardholderName: EditText = findViewById(R.id.etCardholderName)
        val etCardNumber: EditText = findViewById(R.id.etCardNumber)
        val etExpiryMonth: EditText = findViewById(R.id.etExpiryMonth)
        val etExpiryYear: EditText = findViewById(R.id.etExpiryYear)
        val etCVV: EditText = findViewById(R.id.etCVV)
        val etBillingAddress: EditText = findViewById(R.id.etBillingAddress)
        val btnSubmitPayment: Button = findViewById(R.id.btnSubmitPayment)


        tvPaymentAmount.text = "Amount to Pay: $paymentAmount"


        btnSubmitPayment.setOnClickListener {
            val cardholderName = etCardholderName.text.toString()
            val cardNumber = etCardNumber.text.toString()
            val expiryMonth = etExpiryMonth.text.toString()
            val expiryYear = etExpiryYear.text.toString()
            val cvv = etCVV.text.toString()
            val billingAddress = etBillingAddress.text.toString()


            if (cardholderName.isEmpty() || cardNumber.isEmpty() || expiryMonth.isEmpty() ||
                expiryYear.isEmpty() || cvv.isEmpty() || billingAddress.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_fill_all_fields), Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, PaymentSuccessActivity::class.java)
                intent.putExtra("payment_amount", paymentAmount)
                intent.putExtra("cardholder_name", cardholderName)
                startActivity(intent)
            }
        }
    }
}
