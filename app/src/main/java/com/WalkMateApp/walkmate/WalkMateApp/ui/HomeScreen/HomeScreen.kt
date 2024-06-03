package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.DashboardColumn
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.DropdownRowWithBarChart
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.GreetingRow
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.HeartRateRow
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoutes.SettingsScreen.route)
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(35.dp)
                            .clip(RoundedCornerShape(10))
                            .background(TwilightBlue)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_box),
                            contentDescription = "Settings Icon",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                actions = {

                    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .clickable {
                                navController.navigate(ScreenRoutes.ProfileScreen.route)
                            })
                },
            )
        }, containerColor = MidnightBlue
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(14.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            GreetingRow()
            DashboardColumn()
            DropdownRowWithBarChart()
            HeartRateRow()
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}


