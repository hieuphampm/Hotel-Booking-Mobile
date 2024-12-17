package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPolicyBinding

class PolicyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val policyText = intent.getStringExtra("policy_text") ?: getString(R.string.privacy_policy_text)

        binding.tvPolicyText.text = policyText

        binding.btnBack.setOnClickListener { finish() }
    }
}
