package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun GreetingRow(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hello, Jenifer", style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White, fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Mon,16 May 2022",
                style = TextStyle(fontSize = 14.sp,
                    color = Color.White),
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(TwilightBlue)
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Goal: 8000 steps",
                style = TextStyle(fontSize = 14.sp,
                    color = Color.White)
            )
        }
    }
}