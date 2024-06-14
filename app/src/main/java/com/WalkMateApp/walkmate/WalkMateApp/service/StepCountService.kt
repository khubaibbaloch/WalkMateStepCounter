package com.WalkMateApp.walkmate.WalkMateApp.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.helperClasses.SharedPreferencesHelper
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

/*class StepCountService : Service() {
    private val NOTIFICATION_ID = 1
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
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(stepCount: Int, stepGoal: Int, calories: Double): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Step Count")
            .setContentText("$stepCount / $stepGoal , $calories cal")
            .setSmallIcon(R.drawable.logo)
            .setSound(null) // Remove sound
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun updateNotification(stepCount: Int, stepGoal: Int, calories: Double) {
        val notification = createNotification(stepCount, stepGoal, calories)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun startStepCounter() {
        sensorManager.registerListener(
            sensorEventListener,
            stepDetectorSensor,
            SensorManager.SENSOR_DELAY_FASTEST
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
            sharedPreferencesHelper.saveData("lastDaySaved", currentDayOfWeek!!)
        } else {
            sharedPreferencesHelper.saveData(currentDayOfWeek, steps.toString())
        }
    }

    private fun saveElapsedTime(time: Long) {
        sharedPreferencesHelper.saveData("elapsedTime", time.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        sharedPreferencesHelper.saveData("elapsedTime", elapsedTime.toString())
        sensorManager.unregisterListener(sensorEventListener)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}

*/

class StepCountService : Service() {
    private val NOTIFICATION_ID = 1
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
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Step Count")
            .setContentText("$stepCount / $stepGoal , $calories cal")
            .setSmallIcon(R.drawable.logo)
            .setSound(null) // Remove sound
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun updateNotification(stepCount: Int, stepGoal: Int, calories: Double) {
        val notification = createNotification(stepCount, stepGoal, calories)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
            sharedPreferencesHelper.saveData("lastDaySaved", currentDayOfWeek!!)
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
