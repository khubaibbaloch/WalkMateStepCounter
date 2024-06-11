package com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutYouTopBar(
    onBackArrowClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .shadow(1.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackArrowClick()
            })
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = WalkMateThemes.colorScheme.tint,
                    modifier = Modifier.size(34.dp)
                )
            }
        },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(end = 34.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "About You",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = WalkMateThemes.colorScheme.textColor
                    )
                )

            }
        },
        actions = {


        }
    )
}