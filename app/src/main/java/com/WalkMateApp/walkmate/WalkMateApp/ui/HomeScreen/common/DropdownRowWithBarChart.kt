package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.ui.theme.green
import java.util.Random

@Composable
fun DropdownRowWithBarChart(viewModel: WalkMateViewModel) {
    var expanded = remember { mutableStateOf(false) }
    var selectedText = remember { mutableStateOf("Today") }

    val stepsMonday = viewModel.getStepsForDay("Monday")
    val stepsTuesday = viewModel.getStepsForDay("Tuesday")
    val stepsWednesday = viewModel.getStepsForDay("Wednesday")
    val stepsThursday = viewModel.getStepsForDay("Thursday")
    val stepsFriday = viewModel.getStepsForDay("Friday")
    val stepsSaturday = viewModel.getStepsForDay("Saturday")
    val stepsSunday = viewModel.getStepsForDay("Sunday")
  //  val maxRange = 15000f
    val maxRange = 15000f
    val yStepSize = 5

    val weekData = listOf(
        1 to stepsMonday,
        2 to stepsTuesday,
        3 to stepsWednesday,
        4 to stepsThursday,
        5 to stepsFriday,
        6 to stepsSaturday,
        7 to stepsSunday
    )

    val barChartData = convertWeekDataToBarData(weekData, maxRange, yStepSize)
    val dayLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")


    val xAxisData = AxisData.Builder()
        .axisLineColor(WalkMateThemes.colorScheme.tint)
        .axisLabelColor(WalkMateThemes.colorScheme.tint)
        .axisStepSize(10.dp)
        .shouldDrawAxisLineTillEnd(true)
        .steps(barChartData.size - 1)
        .bottomPadding(10.dp)
        .startDrawPadding(18.dp)
        .labelData { index -> dayLabels[index] }
        .build()

    val yAxisData = AxisData.Builder()
        .backgroundColor(WalkMateThemes.colorScheme.onBackground)
        .axisLineColor(WalkMateThemes.colorScheme.tint)
        .axisLabelColor(WalkMateThemes.colorScheme.tint)
        .steps(yStepSize)
        .labelAndAxisLinePadding(30.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                WalkMateThemes.colorScheme.onBackground,
                shape = RoundedCornerShape(8.dp)
            )
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
                color = WalkMateThemes.colorScheme.textColor
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                  //  text = selectedText.value,
                    text = "Weekly",
                    fontSize = 14.sp,
                    color = green
                )
                /*IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.White
                    )
                }*/
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
                .height(350.dp),
            barChartData = BarChartData(
                paddingEnd= 0.dp,
                horizontalExtraSpace = 20.dp,
                backgroundColor = WalkMateThemes.colorScheme.onBackground,
                chartData = barChartData,
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                barChartType= BarChartType.VERTICAL
            )
        )

    }

}
fun convertWeekDataToBarData(weekData: List<Pair<Int, Int>>, maxRange: Float, yStepSize: Int): List<BarData> {
    val barDataList = mutableListOf<BarData>()

    weekData.forEach { (listRange, steps) ->
        // Calculate the fraction of the total range
        val fractionOfMaxRange = steps / maxRange

        // Calculate the value on the chart based on the number of steps in the y-axis
        val valueOnChart = fractionOfMaxRange * yStepSize
        val point = Point(x = listRange.toFloat(), y = valueOnChart)
        val label = listRange

        // Generate a random color for the bar
        val color = getRandomColor()

        val barData = BarData(
            point = point,
            label = label.toString(),
            color = color
        )
        barDataList.add(barData)
    }
    return barDataList
}

fun getRandomColor(): Color {
    val random = Random()
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red,green,blue)
}
