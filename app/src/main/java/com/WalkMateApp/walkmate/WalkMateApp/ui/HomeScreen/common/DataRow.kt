package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun DataDetailsRow(){

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
            //Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Yellow)
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
}