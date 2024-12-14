package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find setting option views
        val optionChangePassword: TextView = view.findViewById(R.id.tvSettingOption1)
        val optionManageNotifications: TextView = view.findViewById(R.id.tvSettingOption2)
        val optionPrivacyPolicy: TextView = view.findViewById(R.id.tvSettingOption3)

        // Set click listeners for each option
        optionChangePassword.setOnClickListener {
            Toast.makeText(requireContext(), "Change Password clicked", Toast.LENGTH_SHORT).show()
        }

        optionManageNotifications.setOnClickListener {
            Toast.makeText(requireContext(), "Manage Notifications clicked", Toast.LENGTH_SHORT).show()
        }

        optionPrivacyPolicy.setOnClickListener {
            Toast.makeText(requireContext(), "Privacy Policy clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
