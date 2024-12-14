package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class Room(
    val name: String,
    val price: Double,
    val availability: Boolean,
    val features: List<String>,
    val imageURL: String  // Add imageURL as a String in the primary constructor
) : Parcelable {

    // Constructor to read data from the Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",  // name (String)
        parcel.readDouble(),  // price (Double)
        parcel.readByte() != 0.toByte(),  // availability (Boolean)
        parcel.createStringArrayList() ?: emptyList(),  // features (List<String>)
        parcel.readString() ?: ""  // imageURL (String) - handle default value in case it's null
    )

    // Writing the data to the Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)  // Write name
        parcel.writeDouble(price)  // Write price
        parcel.writeByte(if (availability) 1 else 0)  // Write availability
        parcel.writeStringList(features)  // Write features (List<String>)
        parcel.writeString(imageURL)  // Write imageURL
    }

    // Describes the contents of the Parcelable object
    override fun describeContents(): Int {
        return 0
    }

    // Companion object to create and restore the Room object from a Parcel
    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}
