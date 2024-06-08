package com.WalkMateApp.walkmate.WalkMateApp.ui.StatisticsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.StatisticsScreen.common.SatisticsTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.StatisticsScreen.common.StatisticsItemCard
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Purple80

@Composable
fun StatisticsScreen(navController: NavController,viewModel: WalkMateViewModel) {

    val caloriesBurned by viewModel.caloriesBurned.collectAsState()
    val stepCount by viewModel.stepCount.collectAsState()
    val heartRate = viewModel.heartRate.collectAsState()
    val waterIntake = viewModel.waterIntake.collectAsState()


    Scaffold(
        topBar = {
            SatisticsTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(
                    start = 12.dp, end = 12.dp,
                    bottom = 12.dp,
                    top = 24.dp
                )
                .padding(it)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatisticsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.footstepsicon,
                    iconSize = 24.dp,
                    iconColor = Color.Cyan,
                    mainText = "Today Steps",
                    smallText = "$stepCount"
                )

                StatisticsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.water_intake,
                    iconSize = 18.dp,
                    iconColor = Color.White,
                    mainText = "Water Intake",
                    smallText = "${waterIntake.value} ml",
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatisticsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.calories,
                    iconSize = 20.dp,
                    iconColor = Color.Red,
                    mainText = "Calories",
                    smallText = "$caloriesBurned"
                )

                StatisticsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.heartbeaticon,
                    iconSize = 20.dp,
                    iconColor = Color.Red,
                    mainText = "Heart Rate",
                    smallText = "${heartRate.value} bmp"
                )
            }
        }
    }
}