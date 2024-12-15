package com.example.myapplication.ui.notifications

import android.app.Notification
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AppNotification
import com.example.myapplication.NotificationsAdapter
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NotificationsFragment : Fragment() {

    private lateinit var notificationsAdapter: NotificationsAdapter
    private lateinit var notificationsRecyclerView: RecyclerView
    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val notifications = mutableListOf<AppNotification>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        notificationsRecyclerView = view.findViewById(R.id.rvNotifications)
        notificationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        notificationsAdapter = NotificationsAdapter(notifications)
        notificationsRecyclerView.adapter = notificationsAdapter

        listenForNotifications()

        return view
    }

    private fun listenForNotifications() {
        firestore.collection("notifications")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    notifications.clear()
                    for (doc in snapshot.documents) {
                        val title = doc.getString("title") ?: "No Title"
                        val description = doc.getString("description") ?: "No Description"
                        val roomId = doc.getString("roomType") ?: ""

                        if (roomId.isNotEmpty()) {
                            firestore.collection("rooms").document(roomId)
                                .get()
                                .addOnSuccessListener { roomDoc ->
                                    val roomType = roomDoc.getString("roomType") ?: "Unknown Room"
                                    notifications.add(AppNotification(title, description, roomType))
                                    notificationsAdapter.notifyDataSetChanged()
                                }
                                .addOnFailureListener {
                                    notifications.add(AppNotification(title, description, "Unknown Room"))
                                    notificationsAdapter.notifyDataSetChanged()
                                }
                        } else {
                            notifications.add(AppNotification(title, description, "Unknown Room"))
                            notificationsAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
    }

}