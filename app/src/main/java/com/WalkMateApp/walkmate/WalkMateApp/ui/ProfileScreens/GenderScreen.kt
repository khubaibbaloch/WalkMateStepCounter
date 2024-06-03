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
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun GenderScreen(navController: NavController) {
    var selectedGender =
        remember { mutableStateOf("") } // Maintain the state of the selected gender
    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerpaddind ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpaddind)
                .background(MidnightBlue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Choose Your Gender",
                modifier = Modifier.padding(bottom = 16.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Select your gender to customize your experience",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                style = TextStyle(color = Color.White, fontSize = 16.sp),
                textAlign = TextAlign.Center // Center the text within the column
            )
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
            Button(
                onClick = {
                    selectedGender.value = "Male" // Set the selected gender
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .clip(RoundedCornerShape(10))
                    .background(if (selectedGender.value == "Male") Gray else LightGray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start // Align content to the start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = "Male Icon",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Male",
                        color = if (selectedGender.value == "Male") Color.Black else Color.White
                    ) // Change text color based on selection
                }
            }

            Button(
                onClick = {
                    selectedGender.value = "Female" // Set the selected gender
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10))
                    .background(if (selectedGender.value == "Female") Gray else LightGray),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start // Align content to the start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.female),
                        contentDescription = "Female Icon",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Female",
                        color = if (selectedGender.value == "Female") Color.Black else Color.White
                    ) // Change text color based on selection
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Your privacy is paramount to us. we never share your personal information with any third parties",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    style = TextStyle(color = Color.White, fontSize = 12.sp),
                    textAlign  = TextAlign.Center
                )

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10))
                        .background(Color.Gray),
                    colors= ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = "Continue", color = Color.White)
                }
            }

        }
    }
}
