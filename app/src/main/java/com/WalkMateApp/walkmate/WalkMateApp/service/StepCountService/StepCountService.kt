package com.WalkMateApp.walkmate.WalkMateApp.service.StepCountService

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.helperClasses.SharedPreferencesHelper
import com.WalkMateApp.walkmate.WalkMateApp.service.StepGoalCompleted.CongratulatoryNotificationService
import java.util.Calendar
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class StepCountService : Service() {
    private val NOTIFICATION_ID = 2 // Ensure this ID is unique from other notifications
    private val CHANNEL_ID = "StepCountChannel"
    private val CHANNEL_NAME = "Step Count Notification Channel"

    private var stepCount = 0
    private var stepGoal = 0
    private var calories = 0.0

    private var elapsedTime = 0L
    private var startTime = 0L

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var timer: Timer

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private val stepDetectorSensor by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    }

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                    stepCount++
                    calories = calculateCaloriesBurned(stepCount)
                    updateNotification(stepCount, stepGoal, calories)
                    saveStepCount(stepCount)

                    if (stepCount == stepGoal) {
                        triggerCongratulatoryNotification()
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Not used
        }
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        stepGoal = sharedPreferencesHelper.getData("stepGoal", "0").toInt()
        elapsedTime = sharedPreferencesHelper.getData("elapsedTime", "0").toLong()
        calories = calculateCaloriesBurned(stepCount)

        createNotificationChannel()
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let {
            stepCount = it.getInt("stepCount", 0)
            elapsedTime = it.getLong("elapsedTime", 0L)
            calories = calculateCaloriesBurned(stepCount)
        }
        startForeground(NOTIFICATION_ID, createNotification(stepCount, stepGoal, calories))
        startTime = System.currentTimeMillis()

        startStepCounter()
        startElapsedTimeUpdater()
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for step count notification"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(stepCount: Int, stepGoal: Int, calories: Double): Notification {
        // Inflate the custom layout
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification_layout)

        // Update RemoteViews with actual data
        remoteViews.setTextViewText(R.id.stepCountTextView, stepCount.toString())
        remoteViews.setTextViewText(R.id.caloriesTextView, calories.toString())
        val progress = (stepCount.toDouble() / stepGoal.toDouble() * 100).toInt()
        remoteViews.setProgressBar(R.id.progress_horizontal, 100, progress, false)

        // Build the notification using NotificationCompat
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo) // Set the small icon
            .setContent(remoteViews) // Set custom layout using RemoteViews
            .setSound(null) // Remove sound
            .setPriority(NotificationCompat.PRIORITY_LOW) // Set notification priority
            .build()

        return notification
    }

    private fun updateNotification(stepCount: Int, stepGoal: Int, calories: Double) {
        // Inflate the custom layout
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification_layout)

        // Update RemoteViews with actual data
        remoteViews.setTextViewText(R.id.stepCountTextView, stepCount.toString())
        remoteViews.setTextViewText(R.id.caloriesTextView, calories.toString())
        val progress = (stepCount.toDouble() / stepGoal.toDouble() * 100).toInt()
        remoteViews.setProgressBar(R.id.progress_horizontal, 100, progress, false)

        // Build the notification using NotificationCompat
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo) // Set the small icon
            .setContent(remoteViews) // Set custom layout using RemoteViews
            .setSound(null) // Remove sound
            .setPriority(NotificationCompat.PRIORITY_LOW) // Set notification priority
            .build()

        // Get the NotificationManager and update the notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun startStepCounter() {
        sensorManager.registerListener(
            sensorEventListener,
            stepDetectorSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun startElapsedTimeUpdater() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                elapsedTime += (currentTime - startTime)
                startTime = currentTime
                saveElapsedTime(elapsedTime)
                saveStepCount(stepCount)
            }
        }, 1000, 1000)
    }

    private fun calculateCaloriesBurned(steps: Int): Double {
        val avgCaloriesPerStep = 0.04
        return String.format("%.2f", steps * avgCaloriesPerStep).toDouble()
    }

    private fun saveStepCount(steps: Int) {
        val currentDayOfWeek = Calendar.getInstance()
            .getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val saveLastDay = sharedPreferencesHelper.getData("lastDaySaved", "0")
        if (currentDayOfWeek != saveLastDay) {
            stepCount = 0
            calories = 0.0
            sharedPreferencesHelper.saveData("lastDaySaved", currentDayOfWeek!!)
            updateNotification(stepCount, stepGoal, calories)
        } else {
            sharedPreferencesHelper.saveData(currentDayOfWeek, steps.toString())
        }
    }

    private fun saveElapsedTime(time: Long) {
        val lastSavedDay = sharedPreferencesHelper.getData("lastSavedDay", "")
        val currentDayOfWeek = Calendar.getInstance()
            .getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

        // If the current day is different from the last saved day, reset elapsedTime and save the current day
        if (currentDayOfWeek != lastSavedDay) {
            sharedPreferencesHelper.saveData("elapsedTime", "0")
            sharedPreferencesHelper.saveData("lastSavedDay", currentDayOfWeek!!)
            elapsedTime = 0L
        } else {
            sharedPreferencesHelper.saveData("elapsedTime", time.toString())
        }
    }

    private fun triggerCongratulatoryNotification() {
        val intent = Intent(this, CongratulatoryNotificationService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        sharedPreferencesHelper.saveData("elapsedTime", elapsedTime.toString())
        sensorManager.unregisterListener(sensorEventListener)
        sharedPreferencesHelper.saveBoolean("isPlayingSelected", false)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}


/*class StepCountService : Service() {
    private val NOTIFICATION_ID = 2
    private val CHANNEL_ID = "StepCountChannel"
    private val CHANNEL_NAME = "Step Count Notification Channel"

    private var stepCount = 0
    private var stepGoal = 0
    private var calories = 0.0

    private var elapsedTime = 0L
    private var startTime = 0L

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var timer: Timer

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private val stepDetectorSensor by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    }

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                    stepCount++
                    calories = calculateCaloriesBurned(stepCount)
                    updateNotification(stepCount, stepGoal, calories)
                    saveStepCount(stepCount)

                    if (stepCount == stepGoal) {
                        triggerCongratulatoryNotification()
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Not used
        }
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        stepGoal = sharedPreferencesHelper.getData("stepGoal", "0").toInt()
        elapsedTime = sharedPreferencesHelper.getData("elapsedTime", "0").toLong()
        calories = calculateCaloriesBurned(stepCount)

        createNotificationChannel()
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Handle incoming data from intent
        intent?.extras?.let {
            stepCount = it.getInt("stepCount", 0)
            elapsedTime = it.getLong("elapsedTime", 0L)
            calories = calculateCaloriesBurned(stepCount)
        }

        // For Android 13+ check notification permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                // TODO: Request notification permission before continuing
                stopSelf() // Stop service if permission isn't granted
                return START_NOT_STICKY
            }
        }

        // Start foreground service with notification
        startForeground(NOTIFICATION_ID, createNotification(stepCount, stepGoal, calories))
        startTime = System.currentTimeMillis()

        startStepCounter()
        startElapsedTimeUpdater()
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for step count notification"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(stepCount: Int, stepGoal: Int, calories: Double): Notification {
        // Inflate the custom layout
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification_layout)

        // Update RemoteViews with actual data
        remoteViews.setTextViewText(R.id.stepCountTextView, stepCount.toString())
        remoteViews.setTextViewText(R.id.caloriesTextView, calories.toString())
        val progress = (stepCount.toDouble() / stepGoal.toDouble() * 100).toInt()
        remoteViews.setProgressBar(R.id.progress_horizontal, 100, progress, false)

        // Build the notification using NotificationCompat
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo) // Set the small icon
            .setContent(remoteViews) // Set custom layout using RemoteViews
            .setSound(null) // Remove sound
            .setPriority(NotificationCompat.PRIORITY_LOW) // Set notification priority
            .build()
    }

    private fun updateNotification(stepCount: Int, stepGoal: Int, calories: Double) {
        // Update the notification
        val notification = createNotification(stepCount, stepGoal, calories)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun startStepCounter() {
        sensorManager.registerListener(
            sensorEventListener,
            stepDetectorSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun startElapsedTimeUpdater() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                elapsedTime += (currentTime - startTime)
                startTime = currentTime
                saveElapsedTime(elapsedTime)
                saveStepCount(stepCount)
            }
        }, 1000, 1000)
    }

    private fun calculateCaloriesBurned(steps: Int): Double {
        val avgCaloriesPerStep = 0.04
        return String.format("%.2f", steps * avgCaloriesPerStep).toDouble()
    }

    private fun saveStepCount(steps: Int) {
        val currentDayOfWeek = Calendar.getInstance()
            .getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val saveLastDay = sharedPreferencesHelper.getData("lastDaySaved", "0")
        if (currentDayOfWeek != saveLastDay) {
            stepCount = 0
            calories = 0.0
            sharedPreferencesHelper.saveData("lastDaySaved", currentDayOfWeek!!)
            updateNotification(stepCount, stepGoal, calories)
        } else {
            sharedPreferencesHelper.saveData(currentDayOfWeek, steps.toString())
        }
    }

    private fun saveElapsedTime(time: Long) {
        val lastSavedDay = sharedPreferencesHelper.getData("lastSavedDay", "")
        val currentDayOfWeek = Calendar.getInstance()
            .getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

        // If the current day is different from the last saved day, reset elapsedTime and save the current day
        if (currentDayOfWeek != lastSavedDay) {
            sharedPreferencesHelper.saveData("elapsedTime", "0")
            sharedPreferencesHelper.saveData("lastSavedDay", currentDayOfWeek!!)
            elapsedTime = 0L
        } else {
            sharedPreferencesHelper.saveData("elapsedTime", time.toString())
        }
    }

    private fun triggerCongratulatoryNotification() {
        val intent = Intent(this, CongratulatoryNotificationService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        sharedPreferencesHelper.saveData("elapsedTime", elapsedTime.toString())
        sensorManager.unregisterListener(sensorEventListener)
        sharedPreferencesHelper.saveBoolean("isPlayingSelected", false)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
*/