package com.example.myapplication;

data class Room(
    val id: Int,
    val name: String,
    val type: String,
    val price: Double,
    val isAvailable: Boolean
) {
    val features: CharSequence?
    val availability: CharSequence?
}
