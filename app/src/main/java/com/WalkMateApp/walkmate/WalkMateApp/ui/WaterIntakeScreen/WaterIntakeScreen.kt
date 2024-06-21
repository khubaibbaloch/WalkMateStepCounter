package com.WalkMateApp.walkmate.WalkMateApp.ui.WaterIntakeScreen


import android.Manifest
import android.content.pm.PackageManager
import androidx.annotation.DrawableRes
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.ShowPermissionDeniedDialog
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.isActivityRecognitionSupported
import com.WalkMateApp.walkmate.WalkMateApp.ui.WaterIntakeScreen.common.CircularWaterProgressBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.WaterIntakeScreen.common.WaterIntakeTopBar

@Composable
fun WaterIntakeScreen(navController: NavController, viewModel: WalkMateViewModel) {
    val waterIntake = viewModel.waterIntake.collectAsState()
    val totalWaterIntake = viewModel.waterGoal.collectAsState()
    val selectedVolume = viewModel.waterML.collectAsState()

    val context = LocalContext.current
    var showPermissionDeniedDialog by remember { mutableStateOf(false) }
    var showSensorNotSupportedDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            WaterIntakeTopBar(
                onBackArrowClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.popBackStack()
                    }
                },
                onProfileClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.SettingsScreen.route)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(WalkMateThemes.colorScheme.onBackground),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                // Calculate progress
                val progress = waterIntake.value / totalWaterIntake.value.toFloat()

                CircularWaterProgressBar(
                    progress = progress.toFloat(),
                    intake = waterIntake.value.toFloat(),
                    total = totalWaterIntake.value.toFloat()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(WalkMateThemes.colorScheme.background)
                        .clickable {
                            if (!isActivityRecognitionSupported(context)) {
                                showSensorNotSupportedDialog = true
                            } else {
                                if (ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.ACTIVITY_RECOGNITION
                                    ) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    var newWaterIntake =
                                        waterIntake.value + selectedVolume.value
                                    if (newWaterIntake > totalWaterIntake.value) {
                                        newWaterIntake = totalWaterIntake.value
                                    }
                                    viewModel.updateWaterIntake(newWaterIntake.toString())
                                } else {
                                    // Permission is not granted, show the dialog
                                    showPermissionDeniedDialog = true
                                }
                            }
                        },

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = when (selectedVolume.value) {
                                100 -> R.drawable.water_bottle_100ml
                                150 -> R.drawable.water_bottle_150ml
                                250 -> R.drawable.water_bottle_250ml
                                500 -> R.drawable.water_bottle_500ml
                                else -> R.drawable.water_bottle_in_progress
                            }
                        ),
                        contentDescription = "Add Water",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(80.dp)
                    )
                    Text(
                        text = when (selectedVolume.value) {
                            100 -> "100 ml"
                            150 -> "150 ml"
                            250 -> "250 ml"
                            500 -> "500 ml"
                            else -> "100 ml"
                        },
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(WalkMateThemes.colorScheme.onBackground),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WaterVolumeSelection(selectedVolume = selectedVolume.value) { volume ->
                    viewModel.updateWaterML(volume.toString())
                }
            }

        }
        ShowPermissionDeniedDialog(
            showPermission = showPermissionDeniedDialog,
            onDismiss = { showPermissionDeniedDialog = false },
            context = context
        )
    }
}

@Composable
fun WaterVolumeSelection(selectedVolume: Int, onVolumeSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WaterVolumeItem(
                volume = 100,
                imageResource = R.drawable.water_bottle_100ml,
                selectedVolume = selectedVolume
            ) {
                onVolumeSelected(100)
            }
            WaterVolumeItem(
                volume = 150,
                imageResource = R.drawable.water_bottle_150ml,
                selectedVolume = selectedVolume
            ) {
                onVolumeSelected(150)
            }
            WaterVolumeItem(
                volume = 250,
                imageResource = R.drawable.water_bottle_250ml,
                selectedVolume = selectedVolume
            ) {
                onVolumeSelected(250)
            }
            WaterVolumeItem(
                volume = 500,
                imageResource = R.drawable.water_bottle_500ml,
                selectedVolume = selectedVolume
            ) {
                onVolumeSelected(500)
            }
        }
    }
}

@Composable
fun WaterVolumeItem(
    volume: Int,
    @DrawableRes imageResource: Int,
    selectedVolume: Int,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .background(if (volume == selectedVolume) WalkMateThemes.colorScheme.background else Color.Transparent)
            .clickable(onClick = onItemClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Volume $volume ml",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$volume ml",
            color = if (volume == selectedVolume) WalkMateThemes.colorScheme.textColor else Color.Gray,
            fontSize = 12.sp
        )
    }
}
