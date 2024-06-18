package com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.MeasurementInputField
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.PrivacyNoticeAndConfirmButton
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.ToggleButtonRow
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun UpdateWeightScreen(navController: NavController, viewModel: WalkMateViewModel) {
    // Local state to hold the input values
    var localWeight = remember { mutableStateOf(viewModel.getWeight()) }
    var localIsKgSelected = remember { mutableStateOf(viewModel.isKgSelected()) }

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
                .background(WalkMateThemes.colorScheme.background)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderText(
                title = "What is your weight?",
                description = "Accurate data helps us provide better recommendations"
            )
            ToggleButtonRow(
                isUnitSelected = localIsKgSelected.value,
                onToggle = { newIsKgSelected ->
                    localIsKgSelected.value = newIsKgSelected
                },
                unitType1 = "KG",
                unitType2 = "LB"
            )

            MeasurementInputField(
                value = localWeight.value,
                onValueChange = { newValue ->
                    localWeight.value = newValue
                },
                label = "Enter Weight",
                isError = false
            )

            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (localWeight.value.isNotEmpty()) {
                    // Update ViewModel with the local state values
                    viewModel.updateWeight(newWeight = localWeight.value, isKgSelected = localIsKgSelected.value)
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.HomeScreen.route)
                    }
                } else {
                    Toast.makeText(context, "Please add weight", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

