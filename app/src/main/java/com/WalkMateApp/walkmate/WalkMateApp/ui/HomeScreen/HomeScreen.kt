package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils.getBarChartData
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType

import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.ScreenRoutes
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.DataDetailsRow
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common.HomeScreenTopBar
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.ProgressColor1
import com.WalkMateApp.walkmate.ui.theme.ProgressColor2
import com.WalkMateApp.walkmate.ui.theme.ProgressColor3
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val isWalking = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (!isWalking.value) {
                HomeScreenTopBar(
                    onMenuClick = {

                    },
                    onProfileClick = {})
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
                .padding(innerPadding)
                .padding(14.dp)
                .animateContentSize()
                .verticalScroll(rememberScrollState()),
        ) {
            GreetingRow()

            Spacer(modifier = Modifier.height(24.dp))

            Crossfade(
                animationSpec = tween(400),
                targetState = isWalking.value) { target ->
                when (target) {
                    false -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier =
                                Modifier.size(220.dp)
                            ) {

                                val targetSteps = 8000
                                val stepsWalked = 5500

                                // Steps walked Progress
                                CustomCircularProgress(
                                    canvasSize = 220.dp,
                                    indicatorValue = stepsWalked,
                                    foregroundIndicatorStrokeWidth = 26f,
                                    maxIndicatorValue = targetSteps,
                                    isWalking = isWalking.value
                                )

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.Center)
                                ) {
                                    Text(
                                        text = "3,600", style = TextStyle(
                                            fontSize = 18.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.footsteps),
                                        contentDescription = "Done",
                                        modifier = Modifier.size(45.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }

                    true -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if(isWalking.value) 330.dp else 0.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
//                        val slideAnimation by rememberInfiniteTransition(label = "").animateFloat(
//                            initialValue = -50f,
//                            targetValue = 0f,
//                            animationSpec = infiniteRepeatable(
//                                animation = tween(durationMillis = 1000),
//                                repeatMode = RepeatMode.Reverse
//                            )
//                        )

                            val slideAnimation by rememberInfiniteTransition(label = "").animateFloat(
                                initialValue = -50f,
                                targetValue = 0f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(durationMillis = 1000),
                                    repeatMode = RepeatMode.Reverse
                                )
                            )

                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                DelayedCustomDesign(260.dp, 310)
                                DelayedCustomDesign(220.dp, 260)
                                DelayedCustomDesign(170.dp, 190)
                                DelayedCustomDesign(130.dp, 120)
                                DelayedCustomDesign(90.dp, 50)

                                Icon(
                                    painter = painterResource(id = R.drawable.footsteps),
                                    contentDescription = "",
                                    modifier = Modifier.size(34.dp),
                                    tint = Color.White
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 36.dp)
                                    .offset(y = with(LocalDensity.current) { slideAnimation.toDp() }),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.footsteps),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(34.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))

                                // Text sliding animation
                                Text(
                                    text = "3600",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(54.dp)
                        .shadow(
                            10.dp,
                            ambientColor = ProgressColor2,
                            spotColor = ProgressColor1,
                            shape = CircleShape
                        )
                        .background(
                            brush =
                            Brush.linearGradient(
                                listOf(
                                    ProgressColor1,
                                    ProgressColor3
                                )
                            )
                        )
                        .clickable {
                            isWalking.value = !isWalking.value
                        },

                    ) {
                    Icon(
                        painter = painterResource(
                            id =
                            if (isWalking.value) R.drawable.pauseicon else R.drawable.playicon
                        ),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }


            // Heart rate etc
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .background(
                        color = TwilightBlue,
                        shape = RoundedCornerShape(8.dp)
                    )

                    .padding(vertical = 12.dp, horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calories),
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "120",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Cal", fontSize = 14.sp, color = Color.Red,
                    )
                }

                VerticalDivider(
                    color = Color.LightGray,
                    modifier = Modifier.height(90.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = "Favorite",
                        tint = Color.Cyan,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "14:08",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "Min", fontSize = 14.sp, color = Color.Cyan
                    )
                }

                VerticalDivider(
                    color = Color.LightGray,
                    modifier = Modifier.height(90.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Favorite",
                        tint = Color.Green,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "2.6",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "K.M", fontSize = 14.sp, color = Color.Green
                    )
                }
            }

            AnimatedVisibility(
                enter = fadeIn() + expandVertically(
                    animationSpec = tween(1000)
                ),
                exit = fadeOut() + shrinkVertically(
                    animationSpec = tween(1000)
                ),
                visible = !isWalking.value,
            ) {
                DropdownRowWithBarChart()
            }

            AnimatedVisibility(
                visible = !isWalking.value,
            ) {
                HeartRateRow()
            }
        }
    }
}

@Composable
fun CustomDesign(
    size: Dp,
    shadow: Dp,
    color1: Color,
    color2: Color,
    colorAlpha: Float
) {
    Spacer(
        modifier =
        Modifier
            .size(size)
            .shadow(
                shadow,
                spotColor = color1.copy(colorAlpha),
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color1.copy(colorAlpha),
                        color2.copy(colorAlpha)
                    )
                )
            )

    )
}

@Composable
fun DelayedCustomDesign(size: Dp, delayMillis: Long) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        isVisible = true
    }

    if (isVisible) {
        CustomDesign(
            size = size,
            shadow = when (size) {
                260.dp -> 30.dp
                220.dp -> 30.dp
                170.dp -> 30.dp
                130.dp -> 12.dp
                90.dp -> 2.dp
                else -> 0.dp
            },
            color1 = ProgressColor1,
            color2 = ProgressColor2,
            colorAlpha = when (size) {
                260.dp -> 0.1f
                220.dp -> 0.3f
                170.dp -> 0.5f
                130.dp -> 0.7f
                90.dp -> 0.9f
                else -> 1f
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}


