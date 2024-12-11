package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private lateinit var bannerImageView: ImageView
    private lateinit var searchEditText : EditText
    private lateinit var roomsRecyclerView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        bannerImageView = rootView.findViewById(R.id.bannerImageView)
        searchEditText = rootView.findViewById(R.id.searchEditText)
        roomsRecyclerView = rootView.findViewById(R.id.roomsRecyclerView)
        bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView)

        setEventListeners()

        return rootView
    }

    private fun setEventListeners() {
        bannerImageView.setOnClickListener {
            Toast.makeText(requireContext(), "Banner clicked!", Toast.LENGTH_SHORT).show()
        }
        searchEditText.setOnEditorActionListener { _, _, _ ->
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                Toast.makeText(requireContext(), "Searching for: $query", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter a search query.", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(requireContext(), "Home selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.navigation_profile -> {
                    Toast.makeText(requireContext(), "Profile selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.navigation_notifications -> {
                    Toast.makeText(requireContext(), "Notifications selected", Toast.LENGTH_SHORT)
                        .show()
                    true
                }

                else -> false
            }
        }
    }
}