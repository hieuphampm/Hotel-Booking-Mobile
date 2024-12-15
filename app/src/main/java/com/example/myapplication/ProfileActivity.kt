package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile) // Ensure this XML file is the correct one

        // Find the BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set the initial selected item for the navigation view (in case ProfileActivity is the starting point)
        bottomNavigationView.selectedItemId = R.id.navigation_profile

        // Set listener for item selection
        bottomNavigationView.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    // If ProfileActivity is already selected, don't reload it
                    // This will keep the icon selected as the user is already on the Profile page
                    true
                }
                else -> false
            }
        }
    }

    // Override onBackPressed to handle the back button behavior
    override fun onBackPressed() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // If the current selected tab is not Home, navigate back to Home
        if (bottomNavigationView.selectedItemId != R.id.navigation_profile) {
            bottomNavigationView.selectedItemId = R.id.navigation_profile
        } else {
            // If already on the Home tab, use the default back behavior
            super.onBackPressed()
        }
    }
}
