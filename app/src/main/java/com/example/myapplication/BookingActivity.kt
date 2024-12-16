package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

class BookingActivity : AppCompatActivity() {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var checkInPicker: DatePicker
    private lateinit var checkOutPicker: DatePicker
    private lateinit var serviceContainer: LinearLayout
    private lateinit var totalPriceTextView: TextView
    private val selectedServices = mutableListOf<Pair<String, Double>>()

    private var roomPricePerDay: Double = 0.0
    private var roomId: String? = null
    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        checkInPicker = findViewById(R.id.datePickerCheckIn)
        checkOutPicker = findViewById(R.id.datePickerCheckOut)
        serviceContainer = findViewById(R.id.serviceContainer)
        totalPriceTextView = findViewById(R.id.tvTotalPrice)

        roomId = intent.getStringExtra("ROOM_ID")
        roomId?.let { loadRoomDetails(it) }

        findViewById<Button>(R.id.btnConfirmBooking).setOnClickListener {
            confirmBooking()
        }
    }

    private fun loadRoomDetails(roomId: String) {
        firestore.collection("rooms").document(roomId)
            .get()
            .addOnSuccessListener { document ->
                val room = document.toObject(Room::class.java)
                if (room != null) {
                    findViewById<TextView>(R.id.tvRoomName).text = "Room: ${room.roomType}"

                    roomPricePerDay = room.price
                    findViewById<TextView>(R.id.tvRoomPrice).text = "Room Price: ${formatCurrency(roomPricePerDay)}"

                    val services = listOf(
                        Pair("Breakfast", 100000.0),
                        Pair("Airport Transfer", 300000.0),
                        Pair("Spa Services", 500000.0),
                        Pair("Laundry Service", 50000.0),
                        Pair("Private Vehicle Rental", 1000000.0),
                        Pair("Room Decoration", 400000.0)
                    )

                    services.forEach { (serviceName, servicePrice) ->
                        val checkBox = CheckBox(this)
                        checkBox.text = "$serviceName - ${formatCurrency(servicePrice)}"
                        checkBox.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                selectedServices.add(Pair(serviceName, servicePrice))
                            } else {
                                selectedServices.removeIf { it.first == serviceName }
                            }
                            calculateTotalPrice()
                        }
                        serviceContainer.addView(checkBox)
                    }

                    calculateTotalPrice()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load room details: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun calculateTotalPrice() {
        val checkInDate = getDateFromPicker(checkInPicker)
        val checkOutDate = getDateFromPicker(checkOutPicker)

        val daysDifference = ceil(((checkOutDate.time - checkInDate.time) / (1000 * 60 * 60 * 24)).toDouble()).toInt()
        if (daysDifference <= 0) {
            totalPriceTextView.text = "Invalid dates selected"
            return
        }

        val servicesCost = selectedServices.sumOf { it.second }
        totalPrice = (daysDifference * roomPricePerDay) + servicesCost

        totalPriceTextView.text = "Total Price: ${formatCurrency(totalPrice)}"
    }

    private fun getDateFromPicker(datePicker: DatePicker): Date {
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return calendar.time
    }

    private fun confirmBooking() {
        val checkInDate = getDateFromPicker(checkInPicker)
        val checkOutDate = getDateFromPicker(checkOutPicker)

        if (checkOutDate.before(checkInDate)) {
            Toast.makeText(this, "Invalid booking dates!", Toast.LENGTH_SHORT).show()
            return
        }

        val servicesCost = selectedServices.sumOf { it.second }
        val daysDifference = ceil(((checkOutDate.time - checkInDate.time) / (1000 * 60 * 60 * 24)).toDouble()).toInt()
        totalPrice = (daysDifference * roomPricePerDay) + servicesCost

        val bookingDetails = mapOf(
            "roomId" to roomId,
            "checkInDate" to SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(checkInDate),
            "checkOutDate" to SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(checkOutDate),
            "selectedServices" to selectedServices.map { it.first },
            "totalPrice" to totalPrice
        )

        firestore.collection("bookings")
            .add(bookingDetails)
            .addOnSuccessListener {
                val notification = mapOf(
                    "title" to "Successful Payment",
                    "description" to "Payment of ${formatCurrency(totalPrice)} for your booking was successful.",
                    "timestamp" to System.currentTimeMillis()
                )

                firestore.collection("notifications")
                    .add(notification)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Booking confirmed and notification sent!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, PaymentActivity::class.java)
                        intent.putExtra("TOTAL_PRICE", totalPrice)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to send notification: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to confirm booking: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun formatCurrency(amount: Double): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        return currencyFormatter.format(amount)
    }
}