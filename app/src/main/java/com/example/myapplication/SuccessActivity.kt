package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // Thiết lập nút để quay về trang chủ hoặc giao dịch khác
        findViewById<Button>(R.id.btnReturnHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java) // Chuyển hướng tới MainActivity
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

//        findViewById<Button>(R.id.btnViewDetails).setOnClickListener {
//            val intent = Intent(this, TransactionDetailsActivity::class.java)
//            startActivity(intent)
//        }
    }
}
