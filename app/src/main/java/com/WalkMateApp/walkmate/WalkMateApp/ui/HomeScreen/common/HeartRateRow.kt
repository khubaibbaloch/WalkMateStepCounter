package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun HeartRateRow(){
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
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