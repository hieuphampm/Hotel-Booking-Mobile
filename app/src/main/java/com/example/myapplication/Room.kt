package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class Room(
    val name: String,
<<<<<<< Updated upstream
    val price: String,
    val availability: String,
    val features: String
<<<<<<< HEAD
)
=======
    val price: Double,
    val availability: Boolean,
    val features: List<String>
)
>>>>>>> Stashed changes
=======
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(availability)
        parcel.writeString(features)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}
>>>>>>> debug
