package com.WalkMateApp.walkmate.WalkMateApp.ui.InstructionScreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionScreenTopBar(
    onNavigateClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Instructions",
                    style = TextStyle(
                        color = WalkMateThemes.colorScheme.textColor,
                        fontSize = 20.sp
                    ),

                    )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigateClick()
                },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(10))
                    .background(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Settings Icon",
                    tint = WalkMateThemes.colorScheme.tint,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        actions = {

        },
    )
}