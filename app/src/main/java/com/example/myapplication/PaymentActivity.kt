package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity(), AddCardFragment.OnCardAddedListener {

    private lateinit var tvTotalPrice: TextView
    private lateinit var spinnerPaymentMethod: Spinner
    private lateinit var listViewCards: ListView
    private lateinit var btnAddCard: Button
    private lateinit var btnPay: Button

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val cardList = mutableListOf<String>()
    private lateinit var cardAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod)
        listViewCards = findViewById(R.id.listViewCards)
        btnAddCard = findViewById(R.id.btnAddCard)
        btnPay = findViewById(R.id.btnPay)

        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)
        tvTotalPrice.text = "Total Price: ${formatCurrency(totalPrice)}"

        setupSpinner()
        setupCardList()
        loadCardsFromFirebase()

        btnAddCard.setOnClickListener {
            showAddCardFragment()
        }

        btnPay.setOnClickListener {
            val selectedMethod = spinnerPaymentMethod.selectedItem.toString()

            if (selectedMethod == "Card" && cardList.isEmpty()) {
                Toast.makeText(this, "Please add a card before proceeding", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SuccessActivity::class.java)
            intent.putExtra("PAYMENT_METHOD", selectedMethod)
            intent.putExtra("TOTAL_PRICE", totalPrice)
            startActivity(intent)
        }

        // Lắng nghe sự thay đổi phương thức thanh toán để ẩn/hiện nút Add Card
        spinnerPaymentMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedMethod = spinnerPaymentMethod.selectedItem.toString()
                btnAddCard.visibility = if (selectedMethod == "Card") View.VISIBLE else View.GONE
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                btnAddCard.visibility = View.GONE
            }
        }
    }

    private fun setupSpinner() {
        val paymentMethods = listOf("Card", "Cash", "Bank Transfer")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethods)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPaymentMethod.adapter = adapter
    }

    private fun setupCardList() {
        cardAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cardList)
        listViewCards.adapter = cardAdapter
    }

    private fun loadCardsFromFirebase() {
        firestore.collection("cards").get()
            .addOnSuccessListener { result ->
                cardList.clear()
                for (document in result) {
                    val cardInfo = document.getString("cardInfo")
                    if (cardInfo != null) {
                        cardList.add(cardInfo)
                    }
                }
                cardAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load cards: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun formatCurrency(amount: Double): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        return currencyFormatter.format(amount)
    }

    private fun showAddCardFragment() {
        val fragment = AddCardFragment()
        fragment.show(supportFragmentManager, "AddCardFragment")
    }

    override fun onCardAdded(cardInfo: String) {
        firestore.collection("cards").add(mapOf("cardInfo" to cardInfo))
            .addOnSuccessListener {
                cardList.add(cardInfo)
                cardAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Card saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to add card: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}