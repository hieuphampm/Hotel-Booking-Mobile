package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Ensure this XML file is correct

        // Find the BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set the initial selected item for the navigation view (Home tab in this case)
        bottomNavigationView.selectedItemId = R.id.navigation_home

        // Set listener for item selection
        bottomNavigationView.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_home -> {
                    // Don't start a new instance of HomeActivity if it's already the selected tab
                    if (bottomNavigationView.selectedItemId != R.id.navigation_home) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                    true
                }
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
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
        if (bottomNavigationView.selectedItemId != R.id.navigation_home) {
            bottomNavigationView.selectedItemId = R.id.navigation_home
        } else {
            // If already on the Home tab, use the default back behavior
            super.onBackPressed()
        }
    }
}
