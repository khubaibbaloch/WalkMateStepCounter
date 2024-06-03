package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToggleButtonRow(isUnitSelected: Boolean, onToggle: (Boolean) -> Unit,unitType1: String,unitType2: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(60)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onToggle(true) },
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(if (isUnitSelected) Color.Gray else Color.Transparent),
            shape = RoundedCornerShape(60)
        ) {
            Text(text = unitType1, color = if (isUnitSelected) Color.Black else Color.White)
        }

        Spacer(modifier = Modifier.width(8.dp)) // Spacer to add space between the buttons

        Button(
            onClick = { onToggle(false) },
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(if (isUnitSelected) Color.Transparent else Color.Gray),
            shape = RoundedCornerShape(60)
        ) {
            Text(text = unitType2, color = if (isUnitSelected) Color.White else Color.Black)
        }
    }
}
