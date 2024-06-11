package com.WalkMateApp.walkmate.WalkMateApp.MainViewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.WalkMateApp.walkmate.WalkMateApp.helperClasses.SharedPreferencesHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

class WalkMateViewModel(private val context: Context) : ViewModel() {

    private val sharedPreferencesHelper = SharedPreferencesHelper(context)


    // StateFlow to store name, age, gender, height, weight, and step goal values
    private val _nameAndAge = MutableStateFlow<Pair<String, String>>(Pair("", ""))
    val nameAndAge: StateFlow<Pair<String, String>> get() = _nameAndAge

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> get() = _gender

    private val _height =
        MutableStateFlow<Pair<String, Boolean>>(Pair("", true)) // Pair<height, isCmSelected>
    val height: StateFlow<Pair<String, Boolean>> get() = _height

    private val _weight =
        MutableStateFlow<Pair<String, Boolean>>(Pair("", true)) // Pair<weight, isKgSelected>
    val weight: StateFlow<Pair<String, Boolean>> get() = _weight

    private val _stepGoal = MutableStateFlow("")
    val stepGoal: StateFlow<String> get() = _stepGoal

    private val _stepCount = MutableStateFlow(0)
    val stepCount: StateFlow<Int> get() = _stepCount

    private val _caloriesBurned = MutableStateFlow(0.0)
    val caloriesBurned: StateFlow<Double> get() = _caloriesBurned

    private val _distanceCovered = MutableStateFlow(0.0)
    val distanceCovered: StateFlow<Double> get() = _distanceCovered

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                if (it.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                    _stepCount.value++
                    updateCaloriesBurnedAndDistanceCovered()
                    saveStepCount(_stepCount.value)
                    estimateHeartRate(elapsedTime = elapsedTime, stepCount = stepCount.value)
                }

            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Not used
        }
    }


    // Timer variables
    private val _seconds = MutableStateFlow(0)
    val seconds: StateFlow<Int> = _seconds.asStateFlow()

    private val _minutes = MutableStateFlow(0)
    val minutes: StateFlow<Int> = _minutes.asStateFlow()

    private val _hours = MutableStateFlow(0)
    val hours: StateFlow<Int> = _hours.asStateFlow()

    private var timerStarted = false
    private var startTime = 0L
    private var elapsedTime = 0L

    private val handler = Handler(Looper.getMainLooper())

    private val timerRunnable = object : Runnable {
        override fun run() {
            val currentTime = System.currentTimeMillis()
            val totalTime = currentTime - startTime

            val secondsElapsed = (totalTime / 1000).toInt()
            _seconds.value = secondsElapsed % 60

            val minutesElapsed = secondsElapsed / 60
            _minutes.value = minutesElapsed % 60

            _hours.value = minutesElapsed / 60

            if (timerStarted) {
                handler.postDelayed(this, 1000)
            }
        }
    }

    // LiveData for heart rate
    private val _heartRate = MutableStateFlow(0)
    val heartRate: StateFlow<Int> =  _heartRate

    // LiveData for Water Bottle Ml
    private val _waterML = MutableStateFlow(0)
    val waterML: StateFlow<Int> = _waterML

    // LiveData for Water Intake
    private val _waterIntake= MutableStateFlow(0)
    val waterIntake: StateFlow<Int> = _waterIntake

    // LiveData for Water Goal
    private val _waterGoal= MutableStateFlow(0)
    val waterGoal: StateFlow<Int> = _waterGoal

    // LiveData for Water Goal
    private val _isUserAccountCreated= MutableStateFlow(false)
    val isUserAccountCreated: StateFlow<Boolean> = _isUserAccountCreated

    // LiveData for Theme
    private val _currentTheme = MutableStateFlow("")
    val currentTheme: StateFlow<String> = _currentTheme



    init {
        // Initialize _stepCount with the saved count
        updateStepCount()
        updateCaloriesBurnedAndDistanceCovered()

        // Load saved timer state and elapsed time
        resetElapsedTimeIfWeekChanged()
       // elapsedTime = sharedPreferencesHelper.getData("elapsedTime", "0").toLongOrNull() ?: 0L
        //updateTimerValues(elapsedTime)

        //reset the step days on monday
        resetStepCountsOnWeekend()

        //update the bpm
        estimateHeartRate(elapsedTime = elapsedTime, stepCount = stepCount.value)

        // Retrieve initial value from SharedPreferences of Water Bottle ML
        _waterML.value = getWaterML().toInt()

        // Retrieve initial value from SharedPreferences of Water Intake
        _waterIntake.value = getWaterIntake().toInt()

        // Retrieve initial value from SharedPreferences of Water Goal
        _waterGoal.value = getWaterGoal().toInt()

        // reset the value of water intake if day changed
        resetWaterIntakeIfDayChanged()

        _isUserAccountCreated.value = getUserAccountCreated()

        _currentTheme.value = getTheme()

    }

    // Functions to update values
    fun updateNameAndAge(name: String, age: String) {
        _nameAndAge.value = Pair(name, age)
        sharedPreferencesHelper.saveData("name", name)
        sharedPreferencesHelper.saveData("age", age)
    }

    fun updateGender(newGender: String) {
        _gender.value = newGender
        sharedPreferencesHelper.saveData("gender", newGender)
    }

    fun updateHeight(newHeight: String, isCmSelected: Boolean) {
        _height.value = Pair(newHeight, isCmSelected)
        sharedPreferencesHelper.saveData("height", newHeight)
        sharedPreferencesHelper.saveBoolean("isCmSelected", isCmSelected)
    }

    fun updateWeight(newWeight: String, isKgSelected: Boolean) {
        _weight.value = Pair(newWeight, isKgSelected)
        sharedPreferencesHelper.saveData("weight", newWeight)
        sharedPreferencesHelper.saveBoolean("isKgSelected", isKgSelected)
    }

    fun updateStepGoal(newStepGoal: String) {
        _stepGoal.value = newStepGoal
        sharedPreferencesHelper.saveData("stepGoal", newStepGoal)
    }

    /*fun saveStepCount(steps: Int) {
        sharedPreferencesHelper.saveData("stepCount", steps.toString())
    }*/
    fun saveStepCount(steps: Int) {
        val currentDayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        sharedPreferencesHelper.saveData(currentDayOfWeek!!, steps.toString())
    }
    fun resetDataOnDayChange() {
        val currentDayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val lastRecordedDay = sharedPreferencesHelper.getData("lastRecordedDay", "0")

        if (currentDayOfWeek != lastRecordedDay) {
            _stepCount.value = 0
            updateCaloriesBurnedAndDistanceCovered()
            resetElapsedTimeIfWeekChanged()
            resetStepCountsOnWeekend()

            // Reset water intake
            _waterIntake.value = 0
            updateWaterIntake("0")
            resetWaterIntakeIfDayChanged()


            sharedPreferencesHelper.saveData("lastRecordedDay", currentDayOfWeek!!)
        }
    }

    fun resetStepCountsOnWeekend() {
        val calendar = Calendar.getInstance()
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val resetDone = sharedPreferencesHelper.getBoolean("ResetDone", false)

        if (currentDayOfWeek == Calendar.MONDAY) {
            if (!resetDone) {
                // Reset step count to zero for all days of the week
                sharedPreferencesHelper.saveData("Monday", "0")
                sharedPreferencesHelper.saveData("Tuesday", "0")
                sharedPreferencesHelper.saveData("Wednesday", "0")
                sharedPreferencesHelper.saveData("Thursday", "0")
                sharedPreferencesHelper.saveData("Friday", "0")
                sharedPreferencesHelper.saveData("Saturday", "0")
                sharedPreferencesHelper.saveData("Sunday", "0")

                // Mark the reset as done for this week
                sharedPreferencesHelper.saveBoolean("ResetDone", true)
            }
        } else {
            if (resetDone) {
                // Revert the reset flag if it's not Monday and reset has been done
                sharedPreferencesHelper.saveBoolean("ResetDone", false)
            }
        }
        Log.d("TAG", "resetStepCountsIfNeeded: $resetDone")
    }


    fun updateStepCount(){
        _stepCount.value = getStepsForCurrentDay()
       // _stepCount.value = getSavedStepCount()
    }
    fun updateCaloriesBurnedAndDistanceCovered(){
        _caloriesBurned.value = calculateCaloriesBurned(_stepCount.value)
        _distanceCovered.value = calculateDistanceCovered(_stepCount.value)
    }
    fun resetElapsedTimeIfWeekChanged() {
        val lastSavedDay = sharedPreferencesHelper.getData("lastSavedDay", "")
        val currentDayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

        // If the current day is different from the last saved day, reset elapsedTime and save the current day
        if (currentDayOfWeek != lastSavedDay) {
            sharedPreferencesHelper.saveData("elapsedTime", "0")
            sharedPreferencesHelper.saveData("lastSavedDay", currentDayOfWeek!!)
            elapsedTime = 0L
        } else {
            elapsedTime = sharedPreferencesHelper.getData("elapsedTime", "0").toLongOrNull() ?: 0L
        }

        updateTimerValues(elapsedTime)
    }
    fun updateWaterML(newWaterML: String) {
        _waterML.value = newWaterML.toInt()
        sharedPreferencesHelper.saveData("newWaterML", newWaterML)
    }


    fun updateWaterIntake(newWaterIntake: String) {
        _waterIntake.value = newWaterIntake.toInt()
        sharedPreferencesHelper.saveData("newWaterIntake", newWaterIntake)
    }
    fun updateWaterGoal(newWaterGoal: String) {
        _waterGoal.value = newWaterGoal.toInt()
        sharedPreferencesHelper.saveData("newWaterGoal", newWaterGoal)
    }
    fun resetWaterIntakeIfDayChanged() {
        val currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val lastSavedDayOfWeek = sharedPreferencesHelper.getData("lastSavedDayOfWeek", "0")

        // If the current day of the week is different from the last saved day of the week, reset water intake and save the current day
        if (currentDayOfWeek != lastSavedDayOfWeek.toInt()) {
            _waterIntake.value = 0
            sharedPreferencesHelper.saveData("newWaterIntake", "0")
            sharedPreferencesHelper.saveData("lastSavedDayOfWeek", currentDayOfWeek.toString())
        }
    }
    fun updateUserAccountCreated(AccountCreated: Boolean) {
        _isUserAccountCreated.value = AccountCreated
        sharedPreferencesHelper.saveBoolean("AccountCreated", AccountCreated)
    }
    fun updateTheme(newTheme:String) {
        _currentTheme.value = newTheme
        sharedPreferencesHelper.saveData("newTheme", newTheme)
    }



    // Functions to retrieve values
    fun getGender(): String {
        return sharedPreferencesHelper.getData("gender", "")
    }

    fun getHeight(): String {
        return sharedPreferencesHelper.getData("height", "")
    }

    fun isCmSelected(): Boolean {
        return sharedPreferencesHelper.getBoolean("isCmSelected", true)
    }

    fun getWeight(): String {
        return sharedPreferencesHelper.getData("weight", "")
    }

    fun isKgSelected(): Boolean {
        return sharedPreferencesHelper.getBoolean("isKgSelected", true)
    }
    fun getStepGoal():String {
        val stepGoalString = sharedPreferencesHelper.getData("stepGoal", "0")
        _stepGoal.value = stepGoalString
        return stepGoalString
    }
    fun getName(): String {
        return sharedPreferencesHelper.getData("name", "")
    }

    fun getAge(): String {
        return sharedPreferencesHelper.getData("age", "0")
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
    fun getStepsForCurrentDay(): Int {
        val currentDayOfWeek = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        return sharedPreferencesHelper.getData(currentDayOfWeek!!, "0").toInt()
    }
    fun getStepsForDay(day: String): Int {
        return sharedPreferencesHelper.getData(day, "0").toInt()
    }



    fun startStepCounter() {
       // updateStepCount()
        sensorManager.registerListener(
            sensorEventListener,
            stepDetectorSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        // Initialize _stepCount with the saved count

    }

    fun stopSensor() {
        sensorManager.unregisterListener(sensorEventListener)
    }
    private fun calculateCaloriesBurned(steps: Int): Double {
        val avgCaloriesPerStep = 0.04
        val caloriesBurned = steps * avgCaloriesPerStep
        return String.format("%.2f", caloriesBurned).toDouble()
    }
    private fun calculateDistanceCovered(steps: Int): Double {
        val averageStepsPerKilometer = 1375.0
        val distanceKilometers = steps / averageStepsPerKilometer
        return String.format("%.2f", distanceKilometers).toDouble()
    }
    private fun estimateHeartRate(elapsedTime: Long, stepCount: Int) {
        // Convert elapsed time to minutes
        val elapsedTimeMinutes = elapsedTime / (1000 * 60).toFloat()

        // Calculate steps per minute
        val stepsPerMinute = stepCount / elapsedTimeMinutes

        // Define thresholds for low and high steps per minute
        val lowStepsThreshold = 30
        val highStepsThreshold = 120

        // Assuming 2 steps per second corresponds to 1 heart beat
        val heartRate = when {
            stepsPerMinute <= lowStepsThreshold -> 70 // Low heart rate assumption for low step count
            stepsPerMinute >= highStepsThreshold -> 160 // High heart rate assumption for high step count
            else -> ((stepsPerMinute / 2.5) + 70).toInt() // Standard heart rate calculation
        }

        // Update the heart rate LiveData
        _heartRate.value = heartRate
    }

    fun getWaterML():String {
        return sharedPreferencesHelper.getData("newWaterML", "100")
    }
    fun getWaterIntake():String {
        return sharedPreferencesHelper.getData("newWaterIntake", "0")
    }
    fun getWaterGoal():String {
        return sharedPreferencesHelper.getData("newWaterGoal", "0")
    }
    fun getUserAccountCreated():Boolean {
        return sharedPreferencesHelper.getBoolean("AccountCreated", false)
    }
    fun getTheme():String {
         return sharedPreferencesHelper.getData("newTheme", "1")
    }

    fun startTimer() {
        if (!timerStarted) {
            startTime = if (elapsedTime == 0L) {
                System.currentTimeMillis()
            } else {
                System.currentTimeMillis() - elapsedTime
            }
            timerStarted = true
            sharedPreferencesHelper.saveData("timerRunning", "true")
            handler.postDelayed(timerRunnable, 0)
        }
    }

    fun stopTimer() {
        if (timerStarted) {
            timerStarted = false
            handler.removeCallbacks(timerRunnable)
            elapsedTime = System.currentTimeMillis() - startTime
            sharedPreferencesHelper.saveData("elapsedTime", elapsedTime.toString())
            sharedPreferencesHelper.saveData("timerRunning", "false")
        }
    }
    private fun updateTimerValues(elapsedTime: Long) {
        val secondsElapsed = (elapsedTime / 1000).toInt()
        _seconds.value = secondsElapsed % 60

        val minutesElapsed = secondsElapsed / 60
        _minutes.value = minutesElapsed % 60

        _hours.value = minutesElapsed / 60
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(sensorEventListener)
        stopTimer()
    }
}