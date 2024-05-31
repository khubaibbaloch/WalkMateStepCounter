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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileDetailCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Orange
import com.WalkMateApp.walkmate.ui.theme.Pink80
import com.WalkMateApp.walkmate.ui.theme.Purple80


@Composable
fun ProfileScreen(navController: NavController){
    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
    ){
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
            ProfileDetailCard()

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "About you",
                    iconColor = Pink80,
                    icon = R.drawable.usericon,
                    iconSize = 18.dp
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Reminder",
                    iconColor = Color.Green,
                    icon = R.drawable.bellicon,
                    iconSize = 20.dp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Achievements",
                    valueText = "7K",
                    smallText = "Steps",
                    iconColor = Color.Yellow,
                    icon = R.drawable.medalicon,
                    iconSize = 20.dp
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Hydration",
                    valueText = "1.5",
                    smallText = "Litres",
                    iconColor = Color.Cyan,
                    icon = R.drawable.dropicon,
                    iconSize = 20.dp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "Heart Rate",
                    valueText = "75",
                    smallText = "bpm",
                    iconColor = Color.Red,
                    icon = R.drawable.heartbeaticon,
                    iconSize = 20.dp
                )
                ProfileItemCard(
                    modifier = Modifier.weight(1f),
                    mainText = "K.cal",
                    valueText = "900",
                    smallText = "",
                    iconColor = Orange,
                    icon = R.drawable.fireicon,
                    iconSize = 20.dp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}