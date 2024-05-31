package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.common.utils.DataUtils.getBarChartData
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.Pink40
import com.WalkMateApp.walkmate.ui.theme.Pink80
import com.WalkMateApp.walkmate.ui.theme.PurpleGrey80
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Handle the navigation icon press
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(35.dp)
                            .clip(RoundedCornerShape(10))
                            .background(TwilightBlue)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_box),
                            contentDescription = "Settings Icon",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                actions = {

                    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .clickable {})
                },
            )
        }, containerColor = MidnightBlue
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Hello, Jenifer", style = TextStyle(
                            fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mon,16 May 2022",
                        style = TextStyle(fontSize = 14.sp, color = Color.White),
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10))
                        .background(TwilightBlue),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Goal: 8000 steps",
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(fontSize = 14.sp, color = Color.White)
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.size(250.dp)
                ) {
                    CircularProgressIndicator(
                        trackColor = Color.White,
                        // progress = 50f,
                        strokeWidth = 8.dp, color = Color.Red, modifier = Modifier.size(200.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "5,0000", style = TextStyle(
                                fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.footsteps),
                            contentDescription = "Done",
                            modifier = Modifier.size(45.dp),
                            tint = Color.White
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Red)
                        .size(60.dp)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.Black,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(16.dp)
                    .background(
                        color = TwilightBlue, shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 8.dp),
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
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "Cal", fontSize = 14.sp, color = Color.Red,
                    )
                }

                VerticalDivider(modifier = Modifier.height(100.dp))

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
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "Min", fontSize = 14.sp, color = Color.Cyan
                    )
                }

                VerticalDivider(modifier = Modifier.height(100.dp))

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
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
                    Text(
                        text = "K.M", fontSize = 14.sp, color = Color.Green
                    )
                }
            }
            DropdownRowWithBarChart()

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .background(TwilightBlue, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp) // Adjusted padding
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Start Icon",
                    tint = Color.Red,
                    modifier = Modifier.size(35.dp)
                )

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "Heart Rate",
                        fontSize = 16.sp, // Set text size to 16
                        color = Color.White // Adjusted text color
                    )
                    Text(
                        text = "75 bmp",
                        fontSize = 16.sp, // Set text size to 16
                        color = Color.White // Adjusted text color
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "End Icon",
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }

        }
    }
}


@Composable
fun DropdownRowWithBarChart() {
    var expanded = remember { mutableStateOf(false) }
    var selectedText = remember { mutableStateOf("Today") }


    val dataCategoryOptions = DataCategoryOptions(
        true, false

    )


    val barChartData = getBarChartData(
        listSize = 7,
        maxRange = 7,
        barChartType = BarChartType.VERTICAL,
        dataCategoryOptions = dataCategoryOptions
    )
    // Define xAxisData and yAxisData
    val maxRange = 1000f
    val yStepSize = 5

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barChartData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barChartData[index].label }
        .build()

    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(TwilightBlue, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Steps History",
                fontSize = 14.sp,
                color = Color.White
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedText.value,
                    fontSize = 14.sp,
                    color = Color.Green
                )
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedText.value = "Today"
                        expanded.value = false
                    }, text = { Text("Today") })
                    DropdownMenuItem(onClick = {
                        selectedText.value = "Yesterday"
                        expanded.value = false
                    }, text = {
                        Text("Yesterday")
                    })
                    DropdownMenuItem(onClick = {
                        selectedText.value = "Weekly"
                        expanded.value = false
                    }, text = {
                        Text("Weekly")
                    })
                    DropdownMenuItem(onClick = {
                        selectedText.value = "Monthly"
                        expanded.value = false
                    }, text = {
                        Text("Monthly")
                    })
                }
            }
        }

        // Spacer for some padding
        Spacer(modifier = Modifier.height(16.dp))

        // Bar chart
        BarChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            barChartData = BarChartData(
                chartData = barChartData,
                xAxisData = xAxisData,
                yAxisData = yAxisData
            )
        )

    }

}
