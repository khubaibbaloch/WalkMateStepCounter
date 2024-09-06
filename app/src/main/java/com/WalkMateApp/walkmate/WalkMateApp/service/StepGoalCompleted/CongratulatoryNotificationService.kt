package com.WalkMateApp.walkmate.WalkMateApp.service.StepGoalCompleted

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.WalkMateApp.walkmate.R

class CongratulatoryNotificationService : Service() {
    private val NOTIFICATION_ID = 3 // Ensure this ID is unique from other notifications
    private val CHANNEL_ID = "CongratulatoryChannel"
    private val CHANNEL_NAME = "Congratulatory Notification Channel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        showCongratulatoryNotification()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for congratulatory notification"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showCongratulatoryNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo) // Replace with your congrats icon
            .setContentTitle("Congratulations!")
            .setContentText("You've achieved your step goal for today!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Show the notification when the service is started
        showCongratulatoryNotification()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}