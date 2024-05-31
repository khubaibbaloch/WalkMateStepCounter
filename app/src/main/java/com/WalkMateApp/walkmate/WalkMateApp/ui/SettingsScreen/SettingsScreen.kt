package com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Purple80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SettingsTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                },
                onProfileClick = {
                    navController.navigate(ScreenRoutes.ProfileScreen.route)
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
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.footstepsicon,
                    iconSize = 24.dp,
                    iconColor = Color.Cyan,
                    mainText = "Step goal",
                    smallText = "7K"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.instructionsicon,
                    iconSize = 18.dp,
                    iconColor = Color.Green,
                    mainText = "Workout info",
                    smallText = "Instructions"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.statisticicon,
                    iconSize = 20.dp,
                    iconColor = Purple80,
                    mainText = "Statistics",
                    smallText = "Today's"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.night_modeicon,
                    iconSize = 20.dp,
                    iconColor = Color.White,
                    mainText = "Dark Mode",
                    smallText = "Switch to..."
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.infoinstructionicon,
                    iconSize = 20.dp,
                    iconColor = Color.LightGray,
                    mainText = "Instructions",
                    smallText = "Read all"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.needhelpicon,
                    iconSize = 22.dp,
                    iconColor = Color.Cyan,
                    mainText = "Need help?",
                    smallText = "Questions"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.worldicon,
                    iconSize = 22.dp,
                    iconColor = Purple80,
                    mainText = "Language",
                    smallText = "English"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.contacticon,
                    iconSize = 20.dp,
                    iconColor = Color.Yellow,
                    mainText = "Contact Us",
                    smallText = "Call us at +92...'"
                )
            }
        }
    }
}