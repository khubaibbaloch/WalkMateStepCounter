package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun HeightScreen(navController: NavController) {
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
                text = "How tall are you?",
                modifier = Modifier.padding(bottom = 16.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Distance & speed calculation needs it",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                style = TextStyle(color = Color.White, fontSize = 16.sp),
                textAlign = TextAlign.Center // Center the text within the column
            )
            var isCmSelected = remember { mutableStateOf(true) }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(30))
                    .padding(top = 4.dp, bottom = 4.dp), // Padding to separate buttons from the border
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { isCmSelected.value = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(30))
                       // .border(width = 1.dp, color = if (isCmSelected.value) Color.Transparent else Color.Blue)
                        .background(if (isCmSelected.value) Color.Gray else Color.Transparent),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "CM", color = if (isCmSelected.value) Color.Black else Color.White )
                }

                Spacer(modifier = Modifier.width(8.dp)) // Spacer to add space between the buttons

                Button(
                    onClick = { isCmSelected.value = false },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(30))
                       // .border(width = 1.dp, color = if (isCmSelected.value) Color.Blue else Color.Transparent)
                        .background(if (isCmSelected.value) Color.Transparent else Color.Gray),
                    colors = ButtonDefaults.buttonColors(containerColor  = Color.Transparent)
                ) {
                    Text(text = "IN", color = if (isCmSelected.value) Color.White else Color.Black)
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
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10))
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = "Continue", color = Color.White)
                }
            }

        }
    }
}
