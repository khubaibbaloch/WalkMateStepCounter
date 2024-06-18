package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import co.yml.charts.common.extensions.isNotNull
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.MeasurementInputField
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.PrivacyNoticeAndConfirmButton
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun SetGoalScreen(navController: NavController, viewModel: WalkMateViewModel) {
    val stepGoalTextField = remember { mutableStateOf("") }
    val waterGoalTextField = remember { mutableStateOf("") }
    val context = LocalContext.current

    val stepError = remember { mutableStateOf(false) }
    val waterError = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderText(
                title = "What is your water & step goal for today?",
                description = "Set your daily water & step goal"
            )
            MeasurementInputField(
                value = stepGoalTextField.value,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                        stepGoalTextField.value = newValue
                        if (newValue.toIntOrNull() in 500..15000) {
                            stepError.value = false
                            viewModel.updateStepGoal(newStepGoal = newValue)
                        } else {
                            stepError.value = true
                        }
                    }
                },
                label = "Enter step goal",
                isError = stepError.value
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                if (stepError.value) {
                    Text(
                        text = "Please enter a value between 500 and 15000",
                        color = Color.Red,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }

            MeasurementInputField(
                value = waterGoalTextField.value,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                        waterGoalTextField.value = newValue
                        if (newValue.toIntOrNull() in 500..10000) {
                            waterError.value = false
                            viewModel.updateWaterGoal(newWaterGoal = newValue)
                        } else {
                            waterError.value = true
                        }
                    }
                },
                label = "Enter Water goal",
                isError = waterError.value
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                if (waterError.value) {
                    Text(
                        text = "Please enter a value between 500 ml and 10000 ml",
                        color = Color.Red,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (!stepError.value && !waterError.value && stepGoalTextField.value.isNotEmpty() && waterGoalTextField.value.isNotEmpty()) {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.HomeScreen.route)
                    }

                    viewModel.updateUserAccountCreated(true)
                } else {
                    Toast.makeText(
                        context,
                        "Please enter valid step and water goals",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}
