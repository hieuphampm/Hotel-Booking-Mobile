package com.example.myapplication.com.example.myapplication

data class Room(
    val name: String,
<<<<<<< Updated upstream
    val price: String,
    val availability: String,
    val features: String
)
=======
    val price: Double,
    val availability: Boolean,
    val features: List<String>
)
>>>>>>> Stashed changes
