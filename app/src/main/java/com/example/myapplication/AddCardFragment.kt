package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment

class AddCardFragment : DialogFragment() {

    private lateinit var etCardNumber: EditText
    private lateinit var etCardHolderName: EditText
    private lateinit var etExpiryDate: EditText
    private lateinit var etCvv: EditText
    private lateinit var btnAddCard: Button
    private var cardAddedListener: OnCardAddedListener? = null

    interface OnCardAddedListener {
        fun onCardAdded(cardInfo: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etCardNumber = view.findViewById(R.id.etCardNumber)
        etCardHolderName = view.findViewById(R.id.etCardHolderName)
        etExpiryDate = view.findViewById(R.id.etExpiryDate)
        etCvv = view.findViewById(R.id.etCvv)
        btnAddCard = view.findViewById(R.id.btnAddCard)

        btnAddCard.setOnClickListener {
            val cardNumber = etCardNumber.text.toString().trim()
            val cardHolderName = etCardHolderName.text.toString().trim()
            val expiryDate = etExpiryDate.text.toString().trim()
            val cvv = etCvv.text.toString().trim()

            if (TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(cardHolderName) ||
                TextUtils.isEmpty(expiryDate) || TextUtils.isEmpty(cvv)) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cardNumber.matches(Regex("\\d{16}"))) {
                Toast.makeText(requireContext(), "Invalid card number (16 digits required)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!expiryDate.matches(Regex("\\d{2}/\\d{2}"))) {
                Toast.makeText(requireContext(), "Invalid expiry date format (MM/YY required)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cvv.matches(Regex("\\d{3,4}"))) {
                Toast.makeText(requireContext(), "Invalid CVV (3 or 4 digits required)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cardInfo = "Card: $cardNumber, Holder: $cardHolderName, Expiry: $expiryDate"
            cardAddedListener?.onCardAdded(cardInfo)

            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCardAddedListener) {
            cardAddedListener = context
        } else {
            throw ClassCastException("$context must implement OnCardAddedListener")
        }
    }
}
