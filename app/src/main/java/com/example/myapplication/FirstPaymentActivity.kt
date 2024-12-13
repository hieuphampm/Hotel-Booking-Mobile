package com.example.hotelapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton

class FirstPaymentActivity : AppCompatActivity() {

    private lateinit var etCustomerNotes: EditText
    private lateinit var cbNearElevator: CheckBox
    private lateinit var cbFarFromElevator: CheckBox
    private lateinit var cbNonSmoking: CheckBox
    private lateinit var cbNoCornerRoom: CheckBox
    private lateinit var cbSoundproofRoom: CheckBox
    private lateinit var cbHighFloor: CheckBox
    private lateinit var btnBookNow: MaterialButton
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_payment)

      
        etCustomerNotes = findViewById(R.id.et_customer_notes)
        cbNearElevator = findViewById(R.id.cb_near_elevator)
        cbFarFromElevator = findViewById(R.id.cb_far_from_elevator)
        cbNonSmoking = findViewById(R.id.cb_non_smoking)
        cbNoCornerRoom = findViewById(R.id.cb_no_corner_room)
        cbSoundproofRoom = findViewById(R.id.cb_soundproof_room)
        cbHighFloor = findViewById(R.id.cb_high_floor)
        btnBookNow = findViewById(R.id.btn_book_now)
        layout = findViewById(R.id.layout_first_payment)


        etCustomerNotes.addTextChangedListener {
            validateBookingButton()
        }

        btnBookNow.setOnClickListener {
            handleBooking()
        }
    }

    private fun handleBooking() {
        val notes = etCustomerNotes.text.toString()
        val specialRequests = getSelectedRequests()

 
        if (notes.isNotEmpty() || specialRequests.isNotEmpty()) {
            
            val paymentAmount = "100000"

    
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("payment_amount", paymentAmount) 
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please add notes or special requests.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedRequests(): String {
        val selectedRequests = mutableListOf<String>()

        if (cbNearElevator.isChecked) selectedRequests.add(getString(R.string.near_elevator))
        if (cbFarFromElevator.isChecked) selectedRequests.add(getString(R.string.far_from_elevator))
        if (cbNonSmoking.isChecked) selectedRequests.add(getString(R.string.non_smoking_request))
        if (cbNoCornerRoom.isChecked) selectedRequests.add(getString(R.string.no_corner_room))
        if (cbSoundproofRoom.isChecked) selectedRequests.add(getString(R.string.soundproof_room))
        if (cbHighFloor.isChecked) selectedRequests.add(getString(R.string.high_floor))

        return selectedRequests.joinToString(", ")
    }

    private fun validateBookingButton() {
        val notes = etCustomerNotes.text.toString()
     
        btnBookNow.isEnabled = notes.isNotEmpty() || getSelectedRequests().isNotEmpty()
    }
}

