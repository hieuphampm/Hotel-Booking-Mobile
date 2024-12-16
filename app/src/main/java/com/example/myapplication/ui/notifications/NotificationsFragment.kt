package com.example.myapplication.ui.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AppNotification
import com.example.myapplication.NotificationsAdapter
import com.example.myapplication.R
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    @SuppressLint("NotifyDataSetChanged")
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
                        val timestamp = doc.getLong("timestamp") ?: 0L

                        notifications.add(AppNotification(title, description, SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(timestamp))))
                    }
                    notificationsAdapter.notifyDataSetChanged()
                }
            }
    }


}