package com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common.ProfileTopBar
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.HeaderText
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common.PrivacyNoticeAndConfirmButton
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue


@Composable
fun UpdateUserNameScreens(navController: NavController, viewModel: WalkMateViewModel) {
    // Local state to hold the input values
    var localName = remember { mutableStateOf(viewModel.getName()) }
    var localAge = remember { mutableStateOf(viewModel.getAge()) }

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
                title = "Profile",
                description = "Accurate data helps us provide better recommendations"
            )

            UserInfoInputField(
                value = localName.value,
                onValueChange = { newNameValue ->
                    localName.value = newNameValue
                },
                label = "Enter Name",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            UserInfoInputField(
                value = localAge.value,
                onValueChange = { newAgeValue ->
                    localAge.value = newAgeValue
                },
                label = "Enter Age",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )

            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (localName.value.isNotEmpty() && localAge.value.isNotEmpty()) {
                    // Update ViewModel with the local state values
                    viewModel.updateNameAndAge(name = localName.value, age = localAge.value)
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.navigate(ScreenRoutes.UpdateGenderScreen.route)
                    }
                } else {
                    Toast.makeText(context, "Please enter name and age", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = WalkMateThemes.colorScheme.textColor) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        textStyle = TextStyle(color = WalkMateThemes.colorScheme.textColor),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = WalkMateThemes.colorScheme.textColor,
            unfocusedBorderColor = WalkMateThemes.colorScheme.textColor,
            cursorColor = WalkMateThemes.colorScheme.textColor
        ),
        keyboardOptions = keyboardOptions
    )
}
