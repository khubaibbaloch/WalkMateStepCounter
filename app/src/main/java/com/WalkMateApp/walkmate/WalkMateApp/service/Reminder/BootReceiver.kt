package com.WalkMateApp.walkmate.WalkMateApp.service.Reminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.WalkMateApp.walkmate.WalkMateApp.helperClasses.SharedPreferencesHelper
import java.util.Calendar

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == Intent.ACTION_TIME_CHANGED) {
            // Reset the alarms
            val sharedPreferencesHelper = SharedPreferencesHelper(context)
            setReminder(context,sharedPreferencesHelper)
        }
    }
}

@SuppressLint("ScheduleExactAlarm", "ObsoleteSdkInt")
fun setReminder(context: Context,sharedPreferencesHelper: SharedPreferencesHelper) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // Retrieve saved reminder time and selected weekdays from SharedPreferences
    val savedHour = sharedPreferencesHelper.getData("Hours", "0")?.toIntOrNull() ?: 0
    val savedMinute = sharedPreferencesHelper.getData("Minutes", "0")?.toIntOrNull() ?: 0
    val savedZone = sharedPreferencesHelper.getData("Zone", "AM") ?: "AM"
    val savedSelectedDays = sharedPreferencesHelper.getData("SelectedDays", "") ?: ""

    if (savedSelectedDays.isNotEmpty()) {
        val selectedDays = savedSelectedDays.split(",").mapNotNull { getDayOfWeek(it.trim()) }

        // Get the current time to use as a baseline for scheduling
        val currentTime = System.currentTimeMillis()

        // Iterate through each selected day and schedule an alarm
        for (selectedDay in selectedDays) {
            // Calculate the target time for the selected day
            val calendar = Calendar.getInstance().apply {
                timeInMillis = currentTime

                // Set the time based on saved hour, minute, and AM/PM settings
                set(Calendar.HOUR_OF_DAY, when {
                    savedZone == "AM" && savedHour == 12 -> 0  // 12 AM -> 0 in 24-hour format
                    savedZone == "PM" && savedHour != 12 -> savedHour + 12  // 1 PM - 11 PM -> 13 - 23 in 24-hour format
                    else -> savedHour  // 1 AM - 11 AM and 12 PM -> 1 - 11 and 12 in 24-hour format
                })
                set(Calendar.MINUTE, savedMinute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)

                // Set the day of the week to the selected day
                set(Calendar.DAY_OF_WEEK, selectedDay)

                // If the selected day is before today, move to the next week
                if (getTimeInMillis() <= currentTime) {
                    add(Calendar.DAY_OF_YEAR, 7)
                }
            }

            // Create an intent for the ReminderBroadcastReceiver
            val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
                action = "com.example.walkmate.REMINDER_NOTIFICATION"
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                selectedDay,  // Use a unique identifier (day) as request code
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Set the alarm using setExactAndAllowWhileIdle or setExact for precise timing
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        }
    }
}

// Utility function to convert day names to Calendar.DAY_OF_WEEK constants
private fun getDayOfWeek(dayName: String): Int? {
    return when (dayName.lowercase()) {
        "sunday" -> Calendar.SUNDAY
        "monday" -> Calendar.MONDAY
        "tuesday" -> Calendar.TUESDAY
        "wednesday" -> Calendar.WEDNESDAY
        "thursday" -> Calendar.THURSDAY
        "friday" -> Calendar.FRIDAY
        "saturday" -> Calendar.SATURDAY
        else -> null  // Return null for invalid day names
    }
}