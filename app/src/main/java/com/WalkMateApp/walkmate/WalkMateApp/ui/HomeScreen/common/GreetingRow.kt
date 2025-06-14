package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes

@Composable
fun GreetingRow(setGoal:String,userName:String,todayDate:String){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userName, style = TextStyle(
                    fontSize = 16.sp,
                    color = WalkMateThemes.colorScheme.textColor, fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = todayDate,
                style = TextStyle(fontSize = 14.sp,
                    color = WalkMateThemes.colorScheme.textColor),
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(WalkMateThemes.colorScheme.onBackground)
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = setGoal,
                style = TextStyle(fontSize = 14.sp,
                    color = WalkMateThemes.colorScheme.textColor)
            )
        }
    }
}