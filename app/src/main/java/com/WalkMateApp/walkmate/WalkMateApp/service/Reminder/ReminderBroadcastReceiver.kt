package com.WalkMateApp.walkmate.WalkMateApp.service.Reminder

import android.app.Notification.FOREGROUND_SERVICE_IMMEDIATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.WalkMateApp.walkmate.MainActivity
import com.WalkMateApp.walkmate.R


class ReminderBroadcastReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Handle boot completed action if necessary
        } else {
            Log.d("ReminderBroadcastReceiver", "Received broadcast: ${intent.action}")

            val notificationId = 1 // Ensure this ID is unique from other notifications
            val channelId = "reminder_channel"

            // Create an explicit intent for an Activity in your app
            val resultIntent = Intent(context, MainActivity::class.java)

            val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(resultIntent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            }

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("WalkMate Reminder")
                .setContentText("It's time for your reminder!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent)
                .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
                .setAutoCancel(true)

            // Create or update the notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Reminder Channel"
                val descriptionText = "Channel for WalkMate reminders"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            // Display the notification
            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }
    }
}