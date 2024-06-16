package com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Purple80
import kotlinx.coroutines.flow.map


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, viewModel: WalkMateViewModel) {
    // handling footstep card
    val isFootstepClicked = remember { mutableStateOf(false) }
    val FootstepUserInput = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val errorMessage = "Please enter a value between 500 and 15000"


    // handling  water intake card
    val isWaterIntakeClicked = remember { mutableStateOf(false) }
    val WaterIntakeUserInput = remember { mutableStateOf("") }
    val getWaterGoal = viewModel.waterGoal.collectAsState()
    val errorMessageWaterIntake = "Please enter a value between 500ml and 10000ml"
    val waterIntake = viewModel.waterIntake.collectAsState()

    // handling theme selection (light or dark)
    val isThemeClicked = remember { mutableStateOf(false) }
    val currentTheme = viewModel.currentTheme.collectAsState()
    val isLightThemeSelected = remember { mutableStateOf(currentTheme.value == "2") }



    Scaffold(topBar = {
        SettingsTopBar(onBackArrowClick = {
            navController.popBackStack()
        }, onProfileClick = {
            navController.navigate(ScreenRoutes.ProfileScreen.route)
        }, viewModel.getGender())
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
                .padding(
                    start = 12.dp, end = 12.dp, bottom = 12.dp, top = 24.dp
                )
                .padding(it)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(modifier = Modifier.weight(1f),
                    icon = R.drawable.footstepsicon,
                    iconSize = 24.dp,
                    mainText = "Step goal",
                    smallText = viewModel.getStepGoal(),
                    onCardClick = {
                        isFootstepClicked.value = !isFootstepClicked.value
                    })

                SettingsItemCard(modifier = Modifier.weight(1f),
                    icon = R.drawable.dropicon,
                    iconSize = 18.dp,
                    mainText = "Water Goal",
                    smallText = "${getWaterGoal.value}ml",
                    onCardClick = {
                        isWaterIntakeClicked.value = !isWaterIntakeClicked.value
                    })
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(modifier = Modifier.weight(1f),
                    icon = R.drawable.statisticicon,
                    iconSize = 20.dp,
                    mainText = "Statistics",
                    smallText = "Today's",
                    onCardClick = {
                        navController.navigate(ScreenRoutes.StatisticsScreen.route)
                    })

                SettingsItemCard(modifier = Modifier.weight(1f),
                    icon = R.drawable.night_modeicon,
                    iconSize = 20.dp,
                    mainText = "Dark Mode",
                    smallText = "Switch to...",
                    onCardClick = {
                        isThemeClicked.value = !isThemeClicked.value
                    })
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.infoinstructionicon,
                    iconSize = 20.dp,
                    mainText = "Instructions",
                    smallText = "Read all",
                    onCardClick = {
                        navController.navigate(ScreenRoutes.InstructionScreen.route)
                    }
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.needhelpicon,
                    iconSize = 22.dp,
                    mainText = "About us",
                    smallText = "Info",
                    onCardClick = {
                        navController.navigate(ScreenRoutes.AboutUsScreen.route)
                    }
                )

            }

            /*Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.worldicon,
                    iconSize = 22.dp,
                    iconColor = Purple80,
                    mainText = "Language",
                    smallText = "English"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.contacticon,
                    iconSize = 20.dp,
                    iconColor = Color.Yellow,
                    mainText = "Contact Us",
                    smallText = "Call us at +92...'"
                )
            }*/
        }
    }

    if (isFootstepClicked.value) {
        AlertDialog(
            containerColor = WalkMateThemes.colorScheme.onBackground.copy(1f),
            onDismissRequest = {
                isFootstepClicked.value = false
                FootstepUserInput.value = ""

            },
            confirmButton = {
                Button(
                    onClick = {
                        if (!isError.value) {
                            viewModel.updateStepGoal(FootstepUserInput.value)
                            isFootstepClicked.value = false
                            FootstepUserInput.value = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                ) {
                    Text(text = "Save", color = Color.Black)
                }
            },
            dismissButton = {
                Button(onClick = {
                    isFootstepClicked.value = false
                    FootstepUserInput.value = ""
                }, colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                    Text(text = "Cancel", color = Color.Black)
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.target),
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Step Goal", style = TextStyle(
                            fontSize = 16.sp,
                            color = WalkMateThemes.colorScheme.textColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Recommended: ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold, color = Color.Red
                                )
                            ) {
                                append("6000")
                            }
                            append(" steps/day based on your parameters")
                        },
                        fontSize = 14.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = FootstepUserInput.value,
                        onValueChange = { newValue ->
                            FootstepUserInput.value = newValue
                            val intValue = newValue.toIntOrNull()
                            isError.value = intValue == null || intValue < 500 || intValue > 15000
                        },
                        label = {
                            Text(
                                "Enter your step goal",
                                color = if (isError.value) Color.Red else WalkMateThemes.colorScheme.textColor
                            )
                        },
                        textStyle = TextStyle(color = WalkMateThemes.colorScheme.textColor),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isError.value) Color.Red else WalkMateThemes.colorScheme.textColor,
                            unfocusedBorderColor = if (isError.value) Color.Red else WalkMateThemes.colorScheme.textColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = isError.value
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = errorMessage,
                            color = if (isError.value) Color.Red else Color.Gray,
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                }

            }


        )
    }

    if (isWaterIntakeClicked.value) {
        AlertDialog(containerColor = WalkMateThemes.colorScheme.onBackground, onDismissRequest = {
            isWaterIntakeClicked.value = false
            WaterIntakeUserInput.value = ""
        }, confirmButton = {
            Button(onClick = {
                if (!isError.value) {
                    viewModel.updateWaterGoal(WaterIntakeUserInput.value)
                    if (waterIntake.value >= WaterIntakeUserInput.value.toInt()) {
                        viewModel.updateWaterIntake(WaterIntakeUserInput.value)
                    }
                    isWaterIntakeClicked.value = false
                    WaterIntakeUserInput.value = ""
                }

            }, colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "Save", color = Color.Black)
            }
        }, dismissButton = {
            Button(onClick = {
                isWaterIntakeClicked.value = false
                WaterIntakeUserInput.value = ""
            }, colors = ButtonDefaults.buttonColors(Color.LightGray)) {
                Text(text = "Cancel", color = Color.Black)
            }
        }, title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.water_intake), // Change to water icon
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Water Goal", // Change to Water Intake
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = WalkMateThemes.colorScheme.textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }, text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Recommended: ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold, color = Color.Red
                            )
                        ) {
                            append("2000")
                        }
                        append(" ml/day based on your parameters") // Change to ml/day
                    },
                    fontSize = 14.sp,
                    color = WalkMateThemes.colorScheme.textColor,
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = WaterIntakeUserInput.value,
                    onValueChange = { newValue ->
                        WaterIntakeUserInput.value = newValue
                        val intValue = newValue.toIntOrNull()
                        isError.value = intValue == null || intValue < 500 || intValue > 15000
                    },
                    label = {
                        Text(
                            "Enter your Water Goal in ml",
                            color = if (isError.value) Color.Red else WalkMateThemes.colorScheme.textColor
                        )
                    },
                    textStyle = TextStyle(color = WalkMateThemes.colorScheme.textColor),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isError.value) Color.Red else WalkMateThemes.colorScheme.textColor,
                        unfocusedBorderColor = if (isError.value) Color.Red else Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    isError = isError.value
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = errorMessageWaterIntake,
                        color = if (isError.value) Color.Red else Color.Gray,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            }
        })
    }



    if (isThemeClicked.value) {
        AlertDialog(
            containerColor = WalkMateThemes.colorScheme.onBackground,
            onDismissRequest = {
                isThemeClicked.value = false
                // Update theme when dialog is dismissed
                if (currentTheme.value == "2") isLightThemeSelected.value =
                    true else isLightThemeSelected.value = false

            },
            confirmButton = {
                Button(
                    onClick = {
                        isThemeClicked.value = false
                        if (isLightThemeSelected.value) {
                            viewModel.updateTheme("2")
                        } else {
                            viewModel.updateTheme("1")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text(text = "Save", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isThemeClicked.value = false
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text(text = "Cancel", color = Color.Black)
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Set the theme", style = TextStyle(
                            fontSize = 16.sp,
                            color = WalkMateThemes.colorScheme.textColor,
                        )
                    )
                }
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10f))
                            .clickable { isLightThemeSelected.value = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isLightThemeSelected.value,
                            onClick = { isLightThemeSelected.value = true },
                            colors = RadioButtonDefaults.colors(Color.LightGray)
                        )
                        Text(
                            text = "Light Theme", style = TextStyle(
                                fontSize = 14.sp, color = WalkMateThemes.colorScheme.textColor
                            ), modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10f))
                            .clickable { isLightThemeSelected.value = false },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = !isLightThemeSelected.value,
                            onClick = { isLightThemeSelected.value = false },
                            colors = RadioButtonDefaults.colors(Color.LightGray)
                        )
                        Text(
                            text = "Dark Theme", style = TextStyle(
                                fontSize = 14.sp, color = WalkMateThemes.colorScheme.textColor
                            ), modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            }
        )
    }

}