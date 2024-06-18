package com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.PrivacyNoticeAndConfirmButton
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue

@Composable
fun UpdateGenderScreen(navController: NavController, viewModel: WalkMateViewModel) {
    // Local state to hold the input value
    var localGender = remember { mutableStateOf(viewModel.getGender()) }

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
                title = "Choose Your Gender",
                description = "Select your gender to customize your experience"
            )
            GenderSelectionBody(selectedGender = localGender.value) { gender ->
                localGender.value = gender
            }
            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (localGender.value.isNotEmpty()) {
                    // Update ViewModel with the local state value
                    viewModel.updateGender(localGender.value)
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.UpdateHeightScreen.route)
                    }

                } else {
                    Toast.makeText(context, "Please select a gender", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}


@Composable
fun GenderSelectionBody(selectedGender: String, onGenderSelected: (String) -> Unit) {
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
        iconResId = R.drawable.man,
        onGenderSelected = onGenderSelected
    )

    GenderSelectionButton(
        gender = "Female",
        selectedGender = selectedGender,
        iconResId = R.drawable.female,
        onGenderSelected = onGenderSelected
    )
}

@Composable
fun GenderSelectionButton(
    gender: String,
    selectedGender: String,
    iconResId: Int,
    onGenderSelected: (String) -> Unit,
) {
    Button(
        onClick = { onGenderSelected(gender) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(10))
            .background(if (selectedGender == gender) Gray else LightGray),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(10),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "$gender Icon",
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = gender,
                color = if (selectedGender == gender) Color.Black else Color.White
            )
        }
    }
}