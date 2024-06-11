package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes

@Composable
fun HeartRateAndWaterRow(
    value: String,
    imageRes: Int,
    Tittle: String,
    Check: Boolean,
    onRowClick: () -> Unit,
) {


    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(WalkMateThemes.colorScheme.onBackground)
            .clickable {  onRowClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (Check) {
            Icon(
                painter = painterResource(id = imageRes),
                contentDescription = "Icon",
                tint = Color.Red,
                modifier = Modifier.size(35.dp)
            )
        } else {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Icon",
                modifier = Modifier.size(35.dp)
            )
        }


        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = Tittle,
                fontSize = 16.sp, // Set text size to 16
                color = WalkMateThemes.colorScheme.textColor // Adjusted text color
            )
            Text(
                text = value,
                fontSize = 14.sp, // Set text size to 16
                color = Color.Gray // Adjusted text color
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = "End Icon",
            tint = WalkMateThemes.colorScheme.tint,
            modifier = Modifier.size(34.dp)
        )
    }
}
