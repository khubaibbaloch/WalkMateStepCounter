package com.WalkMateApp.walkmate.WalkMateApp.ui.ReminderScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.HeartRateScreen.common.HeartRateTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ReminderScreen.common.ReminderTopBar

@Composable
fun ReminderScreen(navController: NavController, viewModel: WalkMateViewModel) {
    val context = LocalContext.current
    val isTimeClicked = remember { mutableStateOf(false) }
    val reminderHour = viewModel.ReminderHour.collectAsState()
    val reminderMinute = viewModel.ReminderMinute.collectAsState()
    val reminderZone = viewModel.ReminderZone.collectAsState()
    val selectedHour = remember { mutableStateOf(reminderHour.value) }
    val selectedMinute = remember { mutableStateOf(reminderMinute.value) }
    val selectedAmPm = remember { mutableStateOf(reminderZone.value) }
    val isError = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }
    val ReminderEnabled = viewModel.isReminderEnabled.collectAsState()
    val isReminderEnabled = remember { mutableStateOf(ReminderEnabled.value) }
    val selectedDays = remember { mutableStateOf(setOf("Monday")) }
    val isDayDialogVisible = remember { mutableStateOf(false) }
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Fetch selected days from SharedPreferences on screen load
    LaunchedEffect(Unit) {
        val savedDaysString = viewModel.getReminderSelectedDays()
        if (savedDaysString.isNotEmpty()) {
            selectedDays.value = savedDaysString.split(",").toMutableSet()
        }
    }

    Scaffold(
        topBar = {
            ReminderTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                },
                onProfileClick = {
                    navController.navigate(ScreenRoutes.SettingsScreen.route)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { isTimeClicked.value = true }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Reminder",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = WalkMateThemes.colorScheme.textColor
                        )
                    )
                    Text(
                        text = "${reminderHour.value}:${reminderMinute.value} ${reminderZone.value}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = WalkMateThemes.colorScheme.textColor
                        )
                    )
                }
                Switch(
                    checked = isReminderEnabled.value,
                    onCheckedChange = {
                        isReminderEnabled.value = it
                        viewModel.setReminderEnabled(it)
                        if (it) {
                            // Clear the reminder time when the switch is turned off
                            // viewModel.clearReminderTime()
                            // viewModel.setReminder(context)
                        } else {
                            // viewModel.cancelReminder(context)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = WalkMateThemes.colorScheme.primary,
                        uncheckedThumbColor = Color.Gray
                    )
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Repeat Day",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WalkMateThemes.colorScheme.textColor
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = selectedDays.value.joinToString(", "),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor
                    ),
                    modifier = Modifier
                        .clickable { isDayDialogVisible.value = true }
                        .padding(bottom = 8.dp)
                )
            }

        }
    }

    if (isTimeClicked.value) {
        AlertDialog(
            onDismissRequest = {
                isTimeClicked.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (!isError.value) {
                            viewModel.SetReminderTime(
                                selectedHour.value,
                                selectedMinute.value,
                                selectedAmPm.value
                            )
                            isTimeClicked.value = false
                        } else {
                            errorMessage.value = "Please enter a valid time."
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                ) {
                    Text(text = "Save", color = Color.Black)
                }
            },
            dismissButton = {
                Button(onClick = {
                    isTimeClicked.value = false
                }, colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                    Text(text = "Cancel", color = Color.Black)
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.water_intake),
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Set Reminder", style = TextStyle(
                            fontSize = 20.sp,
                            color = WalkMateThemes.colorScheme.textColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = selectedHour.value,
                            onValueChange = { newValue ->
                                selectedHour.value = newValue
                                validateTime(
                                    selectedHour.value,
                                    selectedMinute.value,
                                    selectedAmPm.value,
                                    isError
                                )
                            },
                            label = { Text("Hour") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(70.dp),
                            isError = isError.value,
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = ":",
                            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = selectedMinute.value,
                            onValueChange = { newValue ->
                                selectedMinute.value = newValue
                                validateTime(
                                    selectedHour.value,
                                    selectedMinute.value,
                                    selectedAmPm.value,
                                    isError
                                )
                            },
                            label = { Text("Minute") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(70.dp),
                            isError = isError.value,
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = selectedAmPm.value,
                            onValueChange = { newValue ->
                                selectedAmPm.value = newValue
                                validateTime(
                                    selectedHour.value,
                                    selectedMinute.value,
                                    selectedAmPm.value,
                                    isError
                                )
                            },
                            label = { Text("AM/PM") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier.width(80.dp),
                            isError = isError.value,
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                    }
                    if (isError.value) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage.value,
                            color = Color.Red,
                            style = TextStyle(fontSize = 12.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        )
    }

    if (isDayDialogVisible.value) {
        val tempSelectedDays = remember { mutableStateListOf(*selectedDays.value.toTypedArray()) }

        AlertDialog(
            onDismissRequest = { isDayDialogVisible.value = false },
            title = { Text(text = "Select Days") },
            text = {
                Column {
                    daysOfWeek.forEach { day ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = tempSelectedDays.contains(day),
                                onCheckedChange = { isChecked ->
                                    if (isChecked) {
                                        tempSelectedDays.add(day)
                                    } else {
                                        tempSelectedDays.remove(day)
                                    }
                                }
                            )
                            Text(text = day)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (tempSelectedDays.isNotEmpty()) {
                            // Save selected days to SharedPreferences only when at least one day is selected
                            val selectedDaysString = tempSelectedDays.joinToString(",")
                            viewModel.setReminderSelectedDays(selectedDaysString)
                            selectedDays.value = tempSelectedDays.toSet() // Update selectedDays
                            isDayDialogVisible.value = false // Close the dialog
                        } else {
                            // Optionally, show a message or handle the case where no days are selected
                            // For example:
                            // errorMessage.value = "Please select at least one day."
                        }
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }









    if (isReminderEnabled.value) {
        viewModel.setReminder(context)
    } else {
        viewModel.cancelReminder(context)
    }
}

fun validateTime(hour: String, minute: String, amPm: String, isError: MutableState<Boolean>) {
    val hourInt = hour.toIntOrNull()
    val minuteInt = minute.toIntOrNull()
    val isValidHour = hourInt != null && hourInt in 1..12
    val isValidMinute = minuteInt != null && minuteInt in 0..59
    val isValidAmPm = amPm.equals("AM", ignoreCase = true) || amPm.equals("PM", ignoreCase = true)
    isError.value = !(isValidHour && isValidMinute && isValidAmPm)
}
