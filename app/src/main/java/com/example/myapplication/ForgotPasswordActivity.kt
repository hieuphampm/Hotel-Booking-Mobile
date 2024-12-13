package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()
        val etEmail: EditText = findViewById(R.id.et_email)
        val btnResetPassword: Button = findViewById(R.id.btn_resetPassword)

        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if(email.isEmpty()){
                Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Reset password email sent",Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this,"Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}