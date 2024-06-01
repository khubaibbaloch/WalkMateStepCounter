package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
                    onMenuClick = { /*TODO*/ },
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

            Spacer(modifier = Modifier.height(32.dp))

            if (!isWalking.value) {
                DashboardColumn(
                    isWalking
                )
            } else {
                AnimatedVisibility(
                    visible = isWalking.value,
                    enter = fadeIn(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Box(
                        modifier = Modifier.size(330.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        DelayedCustomDesign(260.dp, 200)
                        DelayedCustomDesign(220.dp, 150)
                        DelayedCustomDesign(170.dp, 100)
                        DelayedCustomDesign(130.dp, 50)
                        DelayedCustomDesign(90.dp, 0)
                        Icon(
                            painter = painterResource(id = R.drawable.footsteps),
                            contentDescription = "",
                            modifier = Modifier.size(34.dp),
                            tint = Color.White
                        )
                    }
                }
            }

            if (isWalking.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.footsteps),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = "3600")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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

            AnimatedVisibility(visible = isWalking.value) {

            }

            Spacer(modifier = Modifier.weight(1f))
            DataDetailsRow()

            if (!isWalking.value) {
                DropdownRowWithBarChart()
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


