package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun DropdownRowWithBarChart() {
    var expanded = remember { mutableStateOf(false) }
    var selectedText = remember { mutableStateOf("Today") }


    val dataCategoryOptions = DataCategoryOptions(
        false, true
    )


    val barChartData = DataUtils.getBarChartData(
        listSize = 31,
        maxRange = 31,
        barChartType = BarChartType.VERTICAL,
        dataCategoryOptions = dataCategoryOptions
    )
    // Define xAxisData and yAxisData
    val maxRange = 1000f
    val yStepSize = 10

    val xAxisData = AxisData.Builder()
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .axisStepSize(10.dp)
        .shouldDrawAxisLineTillEnd(true)
        .steps(barChartData.size - 1)
        .bottomPadding(10.dp)
        .startDrawPadding(18.dp)
        .labelData { index -> "$index" }
        .build()

    val yAxisData = AxisData.Builder()
        .backgroundColor(TwilightBlue)
        .axisLineColor(Color.White)
        .axisLabelColor(Color.White)
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(TwilightBlue,
                shape = RoundedCornerShape(8.dp))
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
                .height(350.dp),
            barChartData = BarChartData(
                paddingEnd= 0.dp,
                horizontalExtraSpace = 20.dp,
                backgroundColor = TwilightBlue,
                chartData = barChartData,
                xAxisData = xAxisData,
                yAxisData = yAxisData
            )
        )

    }

}
