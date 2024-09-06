package com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou.common.AboutYouTopBar


@Composable
fun AboutYouScreen(navController: NavController, viewModel: WalkMateViewModel) {
    val name = viewModel.getName()
    val age = viewModel.getAge()
    val gender = viewModel.getGender()
    val weight = viewModel.getWeight()
    val iskG = viewModel.isKgSelected()
    val height = viewModel.getHeight()
    val isCm = viewModel.isCmSelected()

    Scaffold(
        topBar = {
            AboutYouTopBar(
                onBackArrowClick = {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState
                        == Lifecycle.State.RESUMED
                    ) {
                        navController.popBackStack()
                    }
                },
                onProfileClick = {}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WalkMateThemes.colorScheme.background)
                .padding(
                    bottom = 12.dp,
                    top = 24.dp
                )
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = if (gender == "Female") R.drawable.female else R.drawable.man),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$name",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = WalkMateThemes.colorScheme.textColor.copy(.8f)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Personal info",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = WalkMateThemes.colorScheme.textColor.copy(.8f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(1.dp)
                    .background(WalkMateThemes.colorScheme.onBackground)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp, end = 12.dp,
                            top = 6.dp, bottom = 4.dp
                        )
                ) {
                    UserInfoItem(label = "Name", value = "$name")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp, end = 12.dp,
                            top = 6.dp, bottom = 4.dp
                        )
                ) {
                    UserInfoItem(label = "Age", value = "$age")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp, end = 12.dp,
                            top = 4.dp, bottom = 4.dp
                        )
                ) {
                    UserInfoItem(label = "Gender", value = "$gender")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Body measurements",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = WalkMateThemes.colorScheme.textColor.copy(.8f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(1.dp)
                    .background(WalkMateThemes.colorScheme.onBackground)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp, end = 12.dp,
                            top = 6.dp, bottom = 4.dp
                        )
                ) {
                    UserInfoItem(label = "Weight", value = "$weight ${if (iskG) "KG" else "LB"}")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp, end = 12.dp,
                            top = 6.dp, bottom = 4.dp
                        )
                ) {
                    UserInfoItem(label = "Height", value = "$height ${if (isCm) "CM" else "IN"}")
                }
            }
        }
    }
}

@Composable
fun UserInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 0.dp),
    ) {
        Text(
            text = label,
            color = WalkMateThemes.colorScheme.textColor,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = value,
            color = WalkMateThemes.colorScheme.textColor.copy(.8f),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp, top = 4.dp),

            )
    }
}