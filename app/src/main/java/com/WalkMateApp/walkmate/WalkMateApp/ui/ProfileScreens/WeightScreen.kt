package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.MeasurementInputField
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.PrivacyNoticeAndConfirmButton
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.ToggleButtonRow
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun WeightScreen(navController: NavController,viewModel: WalkMateViewModel) {
    val weightState = viewModel.weight.collectAsState()
    val (weightText, isKgSelected) = weightState.value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.popBackStack()
                    }
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
                title = "What is your weight?",
                description = "Accurate data helps us provide better recommendations"
            )
            ToggleButtonRow(
                isUnitSelected = isKgSelected,
                onToggle = { newIsKgSelected ->
                    viewModel.updateWeight(newWeight = weightText, isKgSelected = newIsKgSelected)
                },
                unitType1 = "KG",
                unitType2 = "LB"
            )

            MeasurementInputField(value = weightText,
                onValueChange = { newValue ->
                    viewModel.updateWeight(newWeight = newValue, isKgSelected = isKgSelected)
                }, label = "Enter Weight",isError = false)

            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (weightText.isNotEmpty()) {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.SetGoalScreen.route)
                    }

                } else {
                    Toast.makeText(context, "Please add height", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

