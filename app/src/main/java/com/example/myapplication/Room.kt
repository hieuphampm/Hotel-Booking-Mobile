package com.example.myapplication

data class Room(
    var id: String = "",
    val roomType: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val amenities: List<String> = listOf(),
    val imageURL: String = ""
)
