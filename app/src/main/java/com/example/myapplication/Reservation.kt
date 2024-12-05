package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.util.*

class ReservationActivity : AppCompatActivity() {

    private lateinit var selectedRoomTextView: TextView
    private lateinit var startDateTextView: TextView
    private lateinit var endDateTextView: TextView
    private lateinit var guestNameEditText: EditText
    private lateinit var confirmButton: Button
    private lateinit var selectRoomButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation_screen)

        selectedRoomTextView = findViewById(R.id.selected_room)
        startDateTextView = findViewById(R.id.start_date)
        endDateTextView = findViewById(R.id.end_date)
        guestNameEditText = findViewById(R.id.guest_name)
        confirmButton = findViewById(R.id.confirm_button)
        selectRoomButton = findViewById(R.id.select_room_button)

        startDateTextView.setOnClickListener {
            showDatePickerDialog { date -> startDateTextView.text = date }
        }

        endDateTextView.setOnClickListener {
            showDatePickerDialog { date -> endDateTextView.text = date }
        }

        selectRoomButton.setOnClickListener {
            selectedRoomTextView.text = "Deluxe Room"
        }

        confirmButton.setOnClickListener {
            val guestName = guestNameEditText.text.toString()
            val startDate = startDateTextView.text.toString()
            val endDate = endDateTextView.text.toString()
            val room = selectedRoomTextView.text.toString()

            if (guestName.isBlank() || startDate == "Select Start Date" || endDate == "Select End Date" || room == "No Room Selected") {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Reservation Confirmed for $guestName!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val date = "${selectedDay}/${selectedMonth + 1}/$selectedYear"
            onDateSelected(date)
        }, year, month, day)

        datePickerDialog.show()
    }
}
