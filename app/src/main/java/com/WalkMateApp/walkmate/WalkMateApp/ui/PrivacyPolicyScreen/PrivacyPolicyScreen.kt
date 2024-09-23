package com.DoNotDisturbBin.DoNotDisturbBinApp.ui.PrivacyPolicyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.ui.theme.statusBarColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 34.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Privacy Policy",
                            color = WalkMateThemes.colorScheme.textColor
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Button",
                            tint = WalkMateThemes.colorScheme.tint
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
                .padding(top = 0.dp, end = 16.dp, start = 16.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    // Title
                    Text(
                        text = "Privacy Policy",
                        fontSize = 24.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Last updated
                    Text(
                        text = "Last updated: [9/22/2024]",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Introduction
                    Text(
                        text = "Introduction",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Welcome to the WalkMate app (\"the App\"). We are committed to protecting your personal information. This privacy policy outlines how we collect, use, and protect the information when you use the App.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Information We Collect
                    Text(
                        text = "Information We Collect",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "WalkMate only collects data related to your physical activity, such as the number of steps you take. This data is necessary to provide you with accurate activity tracking and help you achieve your fitness goals.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // How We Use Your Information
                    Text(
                        text = "How We Use Your Information",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "The data collected is used solely to display your step count and activity statistics within the App. We do not share your activity data with any third parties, and it remains securely stored on your device.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Permissions
                    Text(
                        text = "Permissions",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "The App requires access to your physical activity sensor (via the Physical Activity Permission) to track your steps accurately. This permission is used solely for step counting and related features in the App.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Data Security
                    Text(
                        text = "Data Security",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Your step count data is stored locally on your device, ensuring that no sensitive information is uploaded to the cloud. You can clear your data at any time through the App settings.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Changes to This Privacy Policy
                    Text(
                        text = "Changes to This Privacy Policy",
                        fontSize = 20.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "We may update our privacy policy periodically. If there are significant changes, we will notify you by updating the date at the top of this page.",
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}
