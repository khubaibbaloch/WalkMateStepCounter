package com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsItemCard
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common.SettingsTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Purple80
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(navController: NavController) {

    var isSettingWater by remember { mutableStateOf(true) }


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
            val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9")

            val repeatCount = 1000 // Adjust as needed

            val infiniteList = remember {
                val list = mutableListOf<String>()
                repeat(repeatCount) {
                    list.addAll(items)
                }
                list
            }

            val scrollState = rememberLazyListState()

            val centerItemIndex = remember { items.size / 2 }

            val density = LocalDensity.current

            val initialOffset = remember {
                val itemHeight = 60.dp.toPx(density)
                centerItemIndex * itemHeight - itemHeight / 2
            }

            val centerBgColor = Color.Gray // Background color for the center item

            LaunchedEffect(Unit) {
                scrollState.animateScrollToItem(centerItemIndex, initialOffset.roundToInt())
            }

            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(infiniteList) { index, item ->
                    val highlightColor = if (index % items.size == centerItemIndex) {
                        centerBgColor // Background color for the center item
                    } else {
                        Color.Transparent // Transparent background for other items
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(60.dp) // Adjust as needed
                            .padding(vertical = 8.dp)
                            .background(highlightColor, CircleShape)
                    ) {
                        Text(
                            text = item,
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            }



            /*
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
                    onItemClick = {
                        isSettingWater = !isSettingWater
                    }
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.instructionsicon,
                    iconSize = 18.dp,
                    iconColor = Color.Green,
                    mainText = "Workout info",
                    smallText = "Instructions"
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
                    smallText = "Today's"
                )

                SettingsItemCard(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.night_modeicon,
                    iconSize = 20.dp,
                    iconColor = Color.White,
                    mainText = "Dark Mode",
                    smallText = "Switch to..."
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
             */
        }
    }
}

fun Dp.toPx(density: Density): Float {
    return with(density) { value }
}
