package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    onMenuClick:()->Unit,
    onProfileClick:()->Unit
){
    TopAppBar(
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(
                onClick = {
                   onMenuClick()
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
                       onProfileClick()
                    }
            )
        },
    )
}