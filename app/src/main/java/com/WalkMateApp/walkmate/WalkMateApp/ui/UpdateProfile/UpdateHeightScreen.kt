package com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateHeightScreen(navController: NavController, viewModel: WalkMateViewModel) {
    // Local state to hold the input values
    var localHeight = remember { mutableStateOf(viewModel.getHeight()) }
    var localIsCmSelected = remember { mutableStateOf(viewModel.isCmSelected()) }

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
                title = "How tall are you?",
                description = "Distance & speed calculation needs it"
            )
            ToggleButtonRow(
                isUnitSelected = localIsCmSelected.value,
                onToggle = { newIsCmSelected ->
                    localIsCmSelected.value = newIsCmSelected
                },
                unitType1 = "CM",
                unitType2 = "IN"
            )
            MeasurementInputField(
                value = localHeight.value,
                onValueChange = { newValue ->
                    localHeight.value = newValue
                },
                label = "Enter Height",
                isError = false
            )

            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (localHeight.value.isNotEmpty()) {
                    // Update ViewModel with the local state values
                    viewModel.updateHeight(newHeight = localHeight.value, isCmSelected = localIsCmSelected.value)
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.UpdateWeightScreen.route)
                    }

                } else {
                    Toast.makeText(context, "Please add height", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
