package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Ensure this layout has `bottomNavigationView` and `fragmentContainer`

        // Load the NotificationsFragment when the activity is created
        if (savedInstanceState == null) {
            loadFragment(NotificationsFragment())
        }

        // Find the BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set the selected item as Notifications
        bottomNavigationView.selectedItemId = R.id.navigation_notifications

        // Set listener for navigation item selection
        bottomNavigationView.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_notifications -> {
                    // Do nothing as the user is already on NotificationsActivity
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

    // Method to load fragments into the container
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // Override the back button behavior
    override fun onBackPressed() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Navigate back to Home if the current tab is not Notifications
        if (bottomNavigationView.selectedItemId != R.id.navigation_home) {
            bottomNavigationView.selectedItemId = R.id.navigation_home
        } else {
            // Default behavior if already on the Home tab
            super.onBackPressed()
        }
    }
}
