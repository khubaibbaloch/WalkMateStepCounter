package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileDetailCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.ui.theme.LightGray
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Orange
import com.WalkMateApp.walkmate.ui.theme.Pink80
import com.WalkMateApp.walkmate.ui.theme.Purple80


@Composable
fun ProfileScreen(navController: NavController, viewModel: WalkMateViewModel) {

    val caloriesBurned by viewModel.caloriesBurned.collectAsState()
    val stepCount by viewModel.stepCount.collectAsState()
    val stepGoal by viewModel.stepGoal.collectAsState()
    val heartRate = viewModel.heartRate.collectAsState()
    val profileImg = viewModel.getGender()
    val userName = viewModel.getName()
    val age = viewModel.getAge()
    val waterIntake by viewModel.waterIntake.collectAsState()
    val waterIntakeInLiters = waterIntake / 1000.0
    val remainingSteps = stepGoal.toInt() - stepCount
    val isEditProfileClicked = remember { mutableStateOf(false) }

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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
                .padding(
                    start = 12.dp, end = 12.dp,
                    bottom = 12.dp,
                    top = 24.dp
                )
                .padding(it)
        ) {
            ProfileDetailCard(
                onProfileImg = profileImg,
                userName = userName,
                age = age,
                checkGoal = (stepCount >= stepGoal.toInt()),
                endingMessage = "You need $remainingSteps more steps to reach today's goal.",
                onProfileUpdate = { isEditProfileClicked.value = !isEditProfileClicked.value }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "About you",
                    icon = R.drawable.usericon,
                    iconSize = 18.dp,
                    onClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.navigate(ScreenRoutes.AboutYouScreen.route)
                        }
                    }
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Reminder",
                    icon = R.drawable.bellicon,
                    iconSize = 20.dp,
                    onClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.navigate(ScreenRoutes.ReminderScreen.route)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Achievements",
                    valueText = "$stepCount",
                    smallText = "Steps",
                    icon = R.drawable.medalicon,
                    iconSize = 20.dp,
                    onClick = {}
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Hydration",
                    valueText = "${waterIntakeInLiters}",
                    smallText = "Litres",
                    icon = R.drawable.dropicon,
                    iconSize = 20.dp,
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Heart Rate",
                    valueText = "${heartRate.value}",
                    smallText = "bpm",
                    icon = R.drawable.heartbeaticon,
                    iconSize = 20.dp,
                    onClick = {}
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "K.cal",
                    valueText = "${caloriesBurned}",
                    smallText = "",
                    icon = R.drawable.fireicon,
                    iconSize = 20.dp,
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isEditProfileClicked.value) {
                AlertDialog(
                    containerColor = WalkMateThemes.colorScheme.onBackground,
                    onDismissRequest = { isEditProfileClicked.value = false },
                    title = {
                        Text(
                            text = "Confirm Edit Profile",
                            color = WalkMateThemes.colorScheme.textColor
                        )
                    },
                    text = {
                        Text(
                            "Are you sure you want to edit your profile data? This will not affect other data.",
                            color = WalkMateThemes.colorScheme.textColor
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                isEditProfileClicked.value = false
                                navController.navigate(ScreenRoutes.UpdateUserNameScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(Color.LightGray),
                        ) {
                            Text("Confirm", color = Color.Black)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                isEditProfileClicked.value = false
                            }, colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text("Cancel", color = Color.Black)
                        }
                    }
                )
            }
        }
    }
}