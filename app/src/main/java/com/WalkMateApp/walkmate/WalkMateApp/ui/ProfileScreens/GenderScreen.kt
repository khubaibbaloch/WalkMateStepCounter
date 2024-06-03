package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun GenderScreen(navController: NavController) {
    var selectedGender = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    //   navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MidnightBlue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(
                title = "Choose Your Gender",
                description = "Select your gender to customize your experience"
            )
            GenderSelectionBody(selectedGender)
            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = { navController.navigate(ScreenRoutes.HeightScreen.route) })
        }
    }
}

@Composable
fun GenderSelectionBody(selectedGender: MutableState<String>) {
    IconButton(
        onClick = { /* Handle icon button click */ },
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gender),
            contentDescription = "Gender Icon",
            modifier = Modifier.size(30.dp)
        )
    }

    GenderSelectionButton(
        gender = "Male",
        selectedGender = selectedGender,
        iconResId = R.drawable.man
    )

    GenderSelectionButton(
        gender = "Female",
        selectedGender = selectedGender,
        iconResId = R.drawable.female
    )
}

@Composable
fun GenderSelectionButton(gender: String, selectedGender: MutableState<String>, iconResId: Int) {
    Button(
        onClick = {
            selectedGender.value = gender // Set the selected gender
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(10))
            .background(if (selectedGender.value == gender) Gray else LightGray),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Align content to the start
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$gender Icon",
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = gender,
                color = if (selectedGender.value == gender) Color.Black else Color.White
            ) // Change text color based on selection
        }
    }
}

@Composable
fun PrivacyNoticeAndConfirmButton(onNavigateClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your privacy is paramount to us. We never share your personal information with any third parties",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            style = TextStyle(color = Color.White, fontSize = 12.sp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { onNavigateClick() },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(10)
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }
}
