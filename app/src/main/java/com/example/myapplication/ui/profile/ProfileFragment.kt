package com.example.myapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.example.myapplication.WelcomeActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        tvUserName = view.findViewById(R.id.tvUserName)
        tvUserEmail = view.findViewById(R.id.tvUserEmail)

        val user = auth.currentUser
        if (user != null) {
            tvUserName.text = user.displayName ?: "No Name"
            tvUserEmail.text = user.email ?: "No Email"
        } else {
            tvUserName.text = "No Name"
            tvUserEmail.text = "No Email"
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
        }

        val optionChangePassword: TextView = view.findViewById(R.id.tvSettingOption1)
        val optionPrivacyPolicy: TextView = view.findViewById(R.id.tvSettingOption2)

        optionChangePassword.setOnClickListener {
            Toast.makeText(requireContext(), "Change Password clicked", Toast.LENGTH_SHORT).show()
        }

        optionPrivacyPolicy.setOnClickListener {
            Toast.makeText(requireContext(), "Privacy Policy clicked", Toast.LENGTH_SHORT).show()
        }

        val btnLogOut: View = view.findViewById(R.id.btnLogOut)
        btnLogOut.setOnClickListener {
            auth.signOut()

            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
