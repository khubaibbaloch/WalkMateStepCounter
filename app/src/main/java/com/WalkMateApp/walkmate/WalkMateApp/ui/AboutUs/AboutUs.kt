package com.SoundScapeApp.soundscape.SoundScapeApp.ui.SettingsScreen.AboutUs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(
    navController: NavController
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = { Text(text = "About Us", color = WalkMateThemes.colorScheme.textColor) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.popBackStack()
                        }
                    })
                    {
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
                .padding(16.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WalkMate",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "(Step counter - Pedometer)",

                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color =  WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Version 1.1.1",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color =  WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background( WalkMateThemes.colorScheme.textColor)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Created by",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color =  WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Parvez Mayar & Khubaib Aziz",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color =  WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Owned by KP Creative Labs",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color =  WalkMateThemes.colorScheme.textColor
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "This app was crafted by Parvez Mayar and Khubaib Aziz, two tech enthusiasts from the heart of Lasbela (Balochistan), where the winds of innovation blow faintly. Despite the limited tech landscape, their passion for creating seamless experiences led them to embark on this journey. With WalkMate, they bring you a piece of their world, blending creativity with technology to enhance your health and fitness journey. Join us on this journey toward better health, where every step counts and every achievement tells a story.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                color =  WalkMateThemes.colorScheme.textColor
            )

        }
    }
}