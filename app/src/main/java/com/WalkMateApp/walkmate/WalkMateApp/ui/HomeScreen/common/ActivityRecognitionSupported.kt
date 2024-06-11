package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

fun isActivityRecognitionSupported(context: Context): Boolean {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    return accelerometer != null && gyroscope != null
}
@Composable
fun showSensorNotSupported(showPermission: Boolean, context: Context) {
    if (showPermission) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Sensor Not Supported") },
            text = { Text("Your device does not support the necessary sensors for activity recognition.") },
            confirmButton = {

            },
            dismissButton = {

            }
        )
    }
}