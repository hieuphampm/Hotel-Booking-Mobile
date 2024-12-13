package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        // Get NavController from NavHostFragment
        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Setup BottomNavigationView with NavController
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        // Optional: Handle App Bar if you have one
        // NavigationUI.setupActionBarWithNavController(this, navController)
    }
}
