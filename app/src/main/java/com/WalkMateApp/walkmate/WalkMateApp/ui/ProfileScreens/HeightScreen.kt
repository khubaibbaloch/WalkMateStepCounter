package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
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
fun HeightScreen(navController: NavController, viewModel: WalkMateViewModel) {
    val heightState = viewModel.height.collectAsState()
    val (heightText, isCmSelected) = heightState.value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(innerpadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(
                title = "How tall are you?",
                description = "Distance & speed calculation needs it"
            )
            ToggleButtonRow(
                isUnitSelected = isCmSelected,
                onToggle = { newIsCmSelected ->
                    viewModel.updateHeight(heightText, newIsCmSelected)
                },
                unitType1 = "CM",
                unitType2 = "IN"
            )
            MeasurementInputField(
                value = heightText,
                onValueChange = { newValue ->
                    viewModel.updateHeight(newHeight = newValue, isCmSelected = isCmSelected)
                }, label = "Enter Height",isError = false
            )

            Spacer(modifier = Modifier.weight(1f))
            PrivacyNoticeAndConfirmButton(onNavigateClick = {
                if (heightText.isNotEmpty()) {
                    navController.navigate(ScreenRoutes.WeightScreen.route)
                } else {
                    Toast.makeText(context, "Please add height", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
