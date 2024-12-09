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

        // Get views
        val etAccountNumber: EditText = findViewById(R.id.et_account_number)
        val etAccountName: EditText = findViewById(R.id.et_account_name)
        val etBankName: EditText = findViewById(R.id.et_bank_name)
        val etPaymentAmount: EditText = findViewById(R.id.et_payment_amount)
        val btnConfirmPayment: Button = findViewById(R.id.btn_confirm_payment)

    
        val paymentAmount = intent.getStringExtra("payment_amount") ?: "0"

   
        etPaymentAmount.setText(paymentAmount)

    
        btnConfirmPayment.setOnClickListener {
            val accountNumber = etAccountNumber.text.toString().trim()
            val accountName = etAccountName.text.toString().trim()
            val bankName = etBankName.text.toString().trim()

           
            if (accountNumber.isEmpty() || accountName.isEmpty() || bankName.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
           
                Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show()

               
            }
        }
    }
}
