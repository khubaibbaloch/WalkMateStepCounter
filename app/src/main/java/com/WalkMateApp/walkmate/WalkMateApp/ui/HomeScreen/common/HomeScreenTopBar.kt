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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    onProfileImg: String,
) {
    TopAppBar(
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(10))
                    .background(WalkMateThemes.colorScheme.onBackground),
                onClick = {
                    onMenuClick()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_box),
                    contentDescription = "Settings Icon",
                    tint = WalkMateThemes.colorScheme.tint,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        actions = {

            Image(painter = painterResource(id = if (onProfileImg == "Male") R.drawable.man else R.drawable.female),
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