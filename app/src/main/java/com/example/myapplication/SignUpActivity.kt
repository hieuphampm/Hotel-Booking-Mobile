package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etFullName: EditText = findViewById(R.id.et_full_name)
        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)
        val etBirthDate: EditText = findViewById(R.id.et_birth_date)
        val etPhoneNumber: EditText = findViewById(R.id.et_phone_number)
        val btnSignUp: Button = findViewById(R.id.btn_signup)
        val tvLoginPrompt: TextView = findViewById(R.id.tv_login_prompt)

        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener{
            val fullname = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val birthDate = etBirthDate.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()

            if(fullname.isEmpty() || email.isEmpty() || password.isEmpty() || birthDate.isEmpty() || phoneNumber.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                            if (emailTask.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Sign up successful! Please verify your email.",
                                    Toast.LENGTH_LONG
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Sign up failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        tvLoginPrompt.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}