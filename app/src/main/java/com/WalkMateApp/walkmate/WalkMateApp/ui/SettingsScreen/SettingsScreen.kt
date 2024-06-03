package com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Purple80
import kotlinx.coroutines.flow.map


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    // handling footstep card
    val isFootstepClicked = remember { mutableStateOf(false) }
    val FootstepUserInput = remember { mutableStateOf(TextFieldValue("")) }

    // handling  water intake card
    val isWaterIntakeClicked = remember { mutableStateOf(false) }
    val WaterIntakeUserInput = remember { mutableStateOf(TextFieldValue("")) }

    // handling theme selection (light or dark)
    val isLightThemeSelected = remember { mutableStateOf(false) }
    val isThemeClicked = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            SettingsTopBar(
                onBackArrowClick = {
                    navController.popBackStack()
                },
                onProfileClick = {
                    navController.navigate(ScreenRoutes.ProfileScreen.route)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(
                    start = 12.dp, end = 12.dp,
                    bottom = 12.dp,
                    top = 24.dp
                )
                .padding(it)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.footstepsicon,
                    iconSize = 24.dp,
                    iconColor = Color.Cyan,
                    mainText = "Step goal",
                    smallText = "7K",
                    onCardClick = {
                        isFootstepClicked.value =
                            !isFootstepClicked.value
                    }
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.water_intake,
                    iconSize = 18.dp,
                    iconColor = Color.White,
                    mainText = "Water Intake",
                    smallText = "2000ml",
                    onCardClick = {
                        isWaterIntakeClicked.value =
                            !isWaterIntakeClicked.value
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.statisticicon,
                    iconSize = 20.dp,
                    iconColor = Purple80,
                    mainText = "Statistics",
                    smallText = "Today's",
                    onCardClick = {
                        navController.navigate(ScreenRoutes.StatisticsScreen.route)
                    }
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.night_modeicon,
                    iconSize = 20.dp,
                    iconColor = Color.White,
                    mainText = "Dark Mode",
                    smallText = "Switch to...",
                    onCardClick = {
                        isThemeClicked.value =
                            !isThemeClicked.value
                    }
                )
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
                    iconColor = Color.LightGray,
                    mainText = "Instructions",
                    smallText = "Read all"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.needhelpicon,
                    iconSize = 22.dp,
                    iconColor = Color.Cyan,
                    mainText = "Need help?",
                    smallText = "Questions"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

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
            }
        }
    }

    if (isFootstepClicked.value) {
        AlertDialog(
            containerColor = MidnightBlue,
            onDismissRequest = {
                isFootstepClicked.value = false
                FootstepUserInput.value = TextFieldValue("")
            },
            confirmButton = {
                Button(
                    onClick = {
                        isFootstepClicked.value = false
                        FootstepUserInput.value = TextFieldValue("")
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isFootstepClicked.value = false
                        FootstepUserInput.value = TextFieldValue("")
                    }
                ) {
                    Text("Cancel")
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
                        text = "Step Goal",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Recommended: ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                            ) {
                                append("6000")
                            }
                            append(" steps/day based on your parameters")
                        },
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = FootstepUserInput.value,
                        onValueChange = { newValue ->
                            if (newValue.text.all { it.isDigit() }) {
                                FootstepUserInput.value = newValue
                            }
                        },
                        label = {
                            Text(
                                "Enter your step goal"
                            )
                        },
                        textStyle = TextStyle(color = Color.White),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                }
            }

        )
    }

    if (isWaterIntakeClicked.value) {
        AlertDialog(
            containerColor = MidnightBlue,
            onDismissRequest = {
                isWaterIntakeClicked.value = false
                WaterIntakeUserInput.value = TextFieldValue("")
            },
            confirmButton = {
                Button(
                    onClick = {
                        isWaterIntakeClicked.value = false
                        WaterIntakeUserInput.value = TextFieldValue("")
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isWaterIntakeClicked.value = false
                        WaterIntakeUserInput.value = TextFieldValue("")
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = {
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
                        text = "Water Intake", // Change to Water Intake
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Recommended: ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                            ) {
                                append("2000")
                            }
                            append(" ml/day based on your parameters") // Change to ml/day
                        },
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = WaterIntakeUserInput.value,
                        onValueChange = { newValue ->
                            if (newValue.text.all { it.isDigit() }) {
                                WaterIntakeUserInput.value = newValue
                            }
                        },
                        label = {
                            Text(
                                "Enter your drank water in ml"
                            )
                        },
                        textStyle = TextStyle(color = Color.White),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        )
    }

    if (isThemeClicked.value) {
        AlertDialog(
            containerColor = MidnightBlue,
            onDismissRequest = {
                isThemeClicked.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        isThemeClicked.value = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isThemeClicked.value = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Set the theme",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
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
                        modifier = Modifier.padding(bottom = 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isLightThemeSelected.value,
                            onClick = { isLightThemeSelected.value = true }
                        )
                        Text(
                            text = "Light Theme",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.padding(bottom = 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = !isLightThemeSelected.value,
                            onClick = { isLightThemeSelected.value = false }
                        )
                        Text(
                            text = "Dark Theme",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        )
    }

}