package com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
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
                    navController.popBackStack()
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
                    start = 12.dp, end = 12.dp,
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
            UserInfoItem(label = "Name", value = name)
            UserInfoItem(label = "Age", value = age)
            UserInfoItem(label = "Gender", value = gender)
            UserInfoItem(label = "Weight", value = "$weight ${if (iskG) "KG" else "LB"}")
            UserInfoItem(label = "Height", value = "$height ${if (isCm) "CM" else "IN"}")
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
            color = WalkMateThemes.colorScheme.textColor,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp, top = 4.dp),

        )
    }
}