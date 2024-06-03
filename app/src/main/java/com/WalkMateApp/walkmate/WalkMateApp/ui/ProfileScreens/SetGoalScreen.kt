package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.MeasurementInputField
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun SetGoalScreen(navController: NavController) {
    var stepGoalTextField = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderText(
                title = "What is your step goal for today?",
                description = "Set your daily step goal"
            )
            MeasurementInputField(value = stepGoalTextField.value,
                onValueChange = { newValue ->
                    stepGoalTextField.value = newValue
                }, label = "Enter step goal")
            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndProceed(onNavigateClick = { navController.navigate(ScreenRoutes.HomeScreen.route) })
        }
    }
}

@Composable
fun PrivacyNoticeAndProceed(onNavigateClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your privacy is paramount to us. We never share your personal information with any third parties",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(color = Color.White, fontSize = 12.sp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { onNavigateClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(10)
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }
}
