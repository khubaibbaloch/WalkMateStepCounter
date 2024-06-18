package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.service.StepCountService
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.CustomCircularProgress
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.DropdownRowWithBarChart
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.GreetingRow
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.HeartRateAndWaterRow
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.HomeScreenTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.ShowPermissionDeniedDialog
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.isActivityRecognitionSupported
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.showSensorNotSupported
import com.WalkMateApp.walkmate.ui.theme.ProgressColor1
import com.WalkMateApp.walkmate.ui.theme.ProgressColor2
import com.WalkMateApp.walkmate.ui.theme.ProgressColor3
import com.WalkMateApp.walkmate.ui.theme.green
import com.WalkMateApp.walkmate.ui.theme.lightCyan
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Locale


@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: WalkMateViewModel) {

    var isWalking = viewModel.isPlayingSelected.collectAsState().value
    val caloriesBurned by viewModel.caloriesBurned.collectAsState()
    val distanceCovered by viewModel.distanceCovered.collectAsState()
    val stepCount by viewModel.stepCount.collectAsState()
    val stepGoal = viewModel.getStepGoal().toIntOrNull() ?: 0
    val seconds by viewModel.seconds.collectAsState()
    val minutes by viewModel.minutes.collectAsState()
    val hours by viewModel.hours.collectAsState()
    val elapsedTime = viewModel.elapsedTime.collectAsState()
    val heartRate = viewModel.heartRate.collectAsState()
    val WaterGoal = viewModel.waterGoal.collectAsState()
    val WaterIntake = viewModel.waterIntake.collectAsState()

    val compositionHeart by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.cong))


    val context = LocalContext.current
    var showPermissionDeniedDialog by remember { mutableStateOf(false) }
    var showSensorNotSupportedDialog by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            val permissionName = it.key
            val isGranted = it.value
            if (!isGranted) {
                showPermissionDeniedDialog = true
            }
        }
    }

    LaunchedEffect(Unit) {
        if (!isActivityRecognitionSupported(context)) {
            showSensorNotSupportedDialog = true
        } else {
            val permissionsToRequest = mutableListOf<String>()
            if (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(Manifest.permission.ACTIVITY_RECOGNITION)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context, Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            if (permissionsToRequest.isNotEmpty()) {
                requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }





    Scaffold(
        topBar = {
            if (!isWalking) {
                HomeScreenTopBar(
                    onMenuClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.navigate(ScreenRoutes.SettingsScreen.route)
                        }

                    },
                    onProfileClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.navigate(ScreenRoutes.ProfileScreen.route)
                        }

                    },
                    viewModel.getGender()
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
                .padding(innerPadding)
                .padding(14.dp)
                .animateContentSize()
                .verticalScroll(rememberScrollState()),
        ) {
            GreetingRow(
                setGoal = "Goal: ${viewModel.getStepGoal()} steps",
                userName = "hello, ${viewModel.getName()}",
                todayDate = viewModel.getCurrentDate()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Crossfade(
                animationSpec = tween(400), targetState = isWalking
            ) { target ->
                when (target) {
                    false -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(220.dp)
                            ) {

                                val targetSteps = 8000
                                val stepsWalked = 5500

                                // Steps walked Progress
                                CustomCircularProgress(
                                    canvasSize = 220.dp,
                                    indicatorValue = stepCount,
                                    foregroundIndicatorStrokeWidth = 26f,
                                    maxIndicatorValue = stepGoal,
                                    isWalking = isWalking
                                )

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.Center)
                                ) {
                                    Text(
                                        text = "${stepCount}", style = TextStyle(
                                            fontSize = 18.sp,
                                            color = WalkMateThemes.colorScheme.textColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.footsteps),
                                        contentDescription = "Done",
                                        modifier = Modifier.size(45.dp),
                                        tint = WalkMateThemes.colorScheme.tint
                                    )
                                }
                            }
                        }
                    }

                    true -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (isWalking) 330.dp else 0.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
//                        val slideAnimation by rememberInfiniteTransition(label = "").animateFloat(
//                            initialValue = -50f,
//                            targetValue = 0f,
//                            animationSpec = infiniteRepeatable(
//                                animation = tween(durationMillis = 1000),
//                                repeatMode = RepeatMode.Reverse
//                            )
//                        )

                            val slideAnimation by rememberInfiniteTransition(label = "").animateFloat(
                                initialValue = -50f,
                                targetValue = 0f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(durationMillis = 1000),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = ""
                            )

                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                DelayedCustomDesign(260.dp, 310)
                                DelayedCustomDesign(220.dp, 260)
                                DelayedCustomDesign(170.dp, 190)
                                DelayedCustomDesign(130.dp, 120)
                                DelayedCustomDesign(90.dp, 50)

                                Icon(
                                    painter = painterResource(id = R.drawable.footsteps),
                                    contentDescription = "",
                                    modifier = Modifier.size(34.dp),
                                    tint = WalkMateThemes.colorScheme.tint
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 36.dp)
                                    .offset(y = with(LocalDensity.current) { slideAnimation.toDp() }),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.footsteps),
                                    contentDescription = "",
                                    modifier = Modifier.size(34.dp),
                                    tint = WalkMateThemes.colorScheme.tint
                                )
                                Spacer(modifier = Modifier.width(6.dp))

                                // Text sliding animation
                                Text(
                                    text = "${stepCount}",
                                    color = WalkMateThemes.colorScheme.textColor,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(54.dp)
                        .shadow(
                            10.dp,
                            ambientColor = ProgressColor2,
                            spotColor = ProgressColor1,
                            shape = CircleShape
                        )
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    ProgressColor1, ProgressColor3
                                )
                            )
                        )
                        .clickable {
                            handleWalkingToggle(
                                context = context,
                                viewModel = viewModel,
                                isWalking = isWalking,
                                stepCount = stepCount,
                                elapsedTime = elapsedTime.value,
                                updateIsWalking = { newIsWalking -> isWalking = newIsWalking },
                                showSensorNotSupportedDialog = {
                                    showSensorNotSupportedDialog = true
                                },
                                showPermissionDeniedDialog = { showPermissionDeniedDialog = true }
                            )
                        },

                    /*.clickable {
                        if (!isActivityRecognitionSupported(context)) {
                            showSensorNotSupportedDialog = true
                        } else {
                            if (ContextCompat.checkSelfPermission(
                                    context, Manifest.permission.ACTIVITY_RECOGNITION
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                isWalking = !isWalking
                                viewModel.updatePlayPause(isWalking)
                                if (isWalking){
                                    viewModel.startStepCounter()
                                    viewModel.startTimer()

                                    val intent = Intent(context, StepCountService::class.java)
                                    intent.putExtra("stepCount", stepCount)
                                    intent.putExtra("elapsedTime", elapsedTime.value)
                                    context.startForegroundService(intent)
                                }else{
                                    viewModel.stopTimer()
                                    viewModel.stopSensor()
                                    val intent = Intent(context, StepCountService::class.java)
                                    context.stopService(intent)
                                }

                            } else {
                                // Permission is not granted, show the dialog
                                showPermissionDeniedDialog = true
                            }
                        }

                    }*/

                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isWalking) R.drawable.pauseicon else R.drawable.playicon
                        ),
                        contentDescription = "Play",
                        tint = WalkMateThemes.colorScheme.tint,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }


            // Heart rate etc
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .background(
                        color = WalkMateThemes.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp)
                    )

                    .padding(vertical = 12.dp, horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calories),
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${caloriesBurned}",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Cal", fontSize = 14.sp, color = Color.Red,
                    )
                }

                VerticalDivider(
                    color = Color.LightGray, modifier = Modifier.height(90.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = "Favorite",
                        tint = lightCyan,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = " ${
                            if (hours >= 1) String.format(
                                "%01d:%02d:%02d", hours, minutes, seconds
                            ) else String.format("%02d:%02d", minutes, seconds)
                        }",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "Min", fontSize = 14.sp, color = lightCyan
                    )
                }

                VerticalDivider(
                    color = Color.LightGray, modifier = Modifier.height(90.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Favorite",
                        tint = green,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${distanceCovered}",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "K.M", fontSize = 14.sp, color = green
                    )
                }
            }

            AnimatedVisibility(
                enter = fadeIn() + expandVertically(
                    animationSpec = tween(1000)
                ),
                exit = fadeOut() + shrinkVertically(
                    animationSpec = tween(1000)
                ),
                visible = !isWalking,
            ) {
                Column {

                    HeartRateAndWaterRow(Tittle = "Water Intake",
                        imageRes = R.drawable.water_bottle_in_progress,
                        value = "${WaterIntake.value}ml /${WaterGoal.value}ml",
                        Check = false,
                        onRowClick = { navController.navigate(ScreenRoutes.WaterIntakeScreen.route) })
                    HeartRateAndWaterRow(Tittle = "Heart Rate",
                        imageRes = R.drawable.heart,
                        value = "${heartRate.value} bpm",
                        Check = true,
                        onRowClick = { navController.navigate(ScreenRoutes.HeartRateScreen.route) })
                }
            }

            AnimatedVisibility(
                visible = !isWalking,
            ) {
                DropdownRowWithBarChart(viewModel)
            }
        }

        if (isWalking) {
            viewModel.startStepCounter()
            viewModel.startTimer()
        } else {
            viewModel.stopTimer()
            viewModel.stopSensor()
        }

        if (stepCount == stepGoal && isWalking) {
            StepGoalReachedAnimation(composition = compositionHeart!!, timeoutMillis = 6000L)
        }


        ShowPermissionDeniedDialog(
            showPermission = showPermissionDeniedDialog,
            onDismiss = { showPermissionDeniedDialog = false },
            context = context
        )
        showSensorNotSupported(showSensorNotSupportedDialog, context)
        DayChangeObserver(context = context, viewModel = viewModel)

    }
}

@Composable
fun CustomDesign(
    size: Dp,
    shadow: Dp,
    color1: Color,
    color2: Color,
    colorAlpha: Float,
) {
    Spacer(
        modifier = Modifier
            .size(size)
            .shadow(
                shadow, spotColor = color1.copy(colorAlpha), shape = CircleShape
            )
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color1.copy(colorAlpha), color2.copy(colorAlpha)
                    )
                )
            )

    )
}

@Composable
fun DelayedCustomDesign(size: Dp, delayMillis: Long) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        isVisible = true
    }

    if (isVisible) {
        CustomDesign(
            size = size, shadow = when (size) {
                260.dp -> 30.dp
                220.dp -> 30.dp
                170.dp -> 30.dp
                130.dp -> 12.dp
                90.dp -> 2.dp
                else -> 0.dp
            }, color1 = ProgressColor1, color2 = ProgressColor2, colorAlpha = when (size) {
                260.dp -> 0.1f
                220.dp -> 0.3f
                170.dp -> 0.5f
                130.dp -> 0.7f
                90.dp -> 0.9f
                else -> 1f
            }
        )
    }
}

@Composable
fun StepGoalReachedAnimation(composition: LottieComposition, timeoutMillis: Long = 4000L) {
    var isAnimationFinished by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(timeoutMillis)
        isAnimationFinished = true
    }

    if (!isAnimationFinished) {
        LottieAnimation(
            composition = composition,
            iterations = 1,
        )
    }
}

@Composable
fun DayChangeObserver(context: Context, viewModel: WalkMateViewModel) {
    val currentTime = remember { mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Calendar.getInstance()
            val newMinute = now.get(Calendar.MINUTE)

            if (currentTime.value != newMinute) {
                currentTime.value = newMinute
                viewModel.resetDataOnDayChange()
                //    Toast.makeText(context,"${currentTime.value}",Toast.LENGTH_SHORT).show()
            }

            // Calculate the delay until the start of the next minute
            val delayMillis = (60 - now.get(Calendar.SECOND)) * 1000L
            delay(delayMillis)
        }
    }

}

fun handleWalkingToggle(
    context: Context,
    viewModel: WalkMateViewModel,
    isWalking: Boolean,
    stepCount: Int,
    elapsedTime: Long,
    updateIsWalking: (Boolean) -> Unit,
    showSensorNotSupportedDialog: () -> Unit,
    showPermissionDeniedDialog: () -> Unit
) {
    if (!isActivityRecognitionSupported(context)) {
        showSensorNotSupportedDialog()
    } else {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val newIsWalking = !isWalking
            updateIsWalking(newIsWalking)
            viewModel.updatePlayPause(newIsWalking)
            if (newIsWalking) {
                //  viewModel.startStepCounter()
                //viewModel.startTimer()

                val intent = Intent(context, StepCountService::class.java)
                intent.putExtra("stepCount", stepCount)
                intent.putExtra("elapsedTime", elapsedTime)
                context.startForegroundService(intent)
            } else {
                //viewModel.stopTimer()
                //viewModel.stopSensor()
                val intent = Intent(context, StepCountService::class.java)
                context.stopService(intent)
            }
        } else {
            // Permission is not granted, show the dialog
            showPermissionDeniedDialog()
        }
    }
}
