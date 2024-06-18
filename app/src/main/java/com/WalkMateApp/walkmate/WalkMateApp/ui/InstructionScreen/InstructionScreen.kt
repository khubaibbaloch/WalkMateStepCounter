package com.WalkMateApp.walkmate.WalkMateApp.ui.InstructionScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.HomeScreenTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.InstructionScreen.common.InstructionScreenTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun InstructionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            InstructionScreenTopBar(
                onNavigateClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.popBackStack()
                    }
                }
            )
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
            Text(
                text = "Welcome to WalkMate",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Overview",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "WalkMate is your companion for tracking your daily steps, calories burned, water intake, and more. Our goal is to help you stay active and hydrated while providing insights into your daily activity.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Accuracy of Step Counting",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Our app strives to provide 99% accuracy in step counting. However, the accuracy depends on your device's sensors and the way you carry your phone. Please note that results may vary based on these factors.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Data Representation",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "The data displayed in WalkMate, such as steps taken, calories burned, and water intake, is based on the information you provide and the data collected by your device. This information should not be considered as medical advice or an accurate reflection of your real health status. Use it as a guide to help you stay motivated and active.",
                fontSize = 12.sp,
                color = WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Set Goals",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "You can set daily water intake and step goals within the app. Setting goals can help you stay focused and motivated. Please remember that the data provided is an estimate and may not reflect your actual activity or health status.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Distance Conversion",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "The app converts your step count into the approximate distance traveled. This can help you understand how far you have walked. Keep in mind that this is an estimate and the actual distance may vary.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Heart Rate Monitoring",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "If your device supports it, WalkMate can monitor your heart rate during your activities. This feature relies on your device's sensors and provides an estimate of your heart rate. Please note that it should not be used as a substitute for medical advice or an accurate measure of your heart health.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Calories Burned",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Our app estimates the calories burned based on your step count and personal data (like weight and height). These estimates are approximate and should not be used as an exact measurement of calorie expenditure.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Water Intake",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Tracking your water intake can help you stay hydrated throughout the day. Our app allows you to log your water consumption to ensure you are meeting your daily hydration goals.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Additional Features",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "In addition to step counting, calorie tracking, and water intake, WalkMate offers various features to support your fitness journey. Stay tuned for updates and new features that will enhance your experience.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Disclaimer",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "The data provided by WalkMate is intended for informational purposes only. It is not a substitute for professional medical advice, diagnosis, or treatment. Always seek the advice of your physician or other qualified health provider with any questions you may have regarding a medical condition.",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Thank you for choosing WalkMate. We hope it helps you stay active and healthy!",
                fontSize = 12.sp,
                color =  WalkMateThemes.colorScheme.textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}