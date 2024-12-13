package com.example.myapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class NotificationsFragment : Fragment() {

    private lateinit var notificationsAdapter: NotificationsAdapter
    private lateinit var notificationsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Initialize RecyclerView
        notificationsRecyclerView = view.findViewById(R.id.rvNotifications)
        notificationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize data
        val notifications = listOf(
            Notification("Your booking is confirmed!", "Hotel Sunshine, Room 305"),
            Notification("Check-in reminder", "Don’t forget to check-in tomorrow at Hotel Blossom."),
            Notification("Special Offer", "Get 10% off on your next booking. Limited time offer!"),
            Notification("Room Upgrade!", "Your room has been upgraded to a deluxe suite."),
        )

        // Set adapter
        notificationsAdapter = NotificationsAdapter(notifications)
        notificationsRecyclerView.adapter = notificationsAdapter

        return view
    }
}

// Data model for Notification
data class Notification(val title: String, val description: String)

// Adapter for RecyclerView
class NotificationsAdapter(private val notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int = notifications.size

    // ViewHolder for Notification item
    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvNotificationTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvNotificationDescription)

        fun bind(notification: Notification) {
            titleTextView.text = notification.title
            descriptionTextView.text = notification.description
        }
    }
}