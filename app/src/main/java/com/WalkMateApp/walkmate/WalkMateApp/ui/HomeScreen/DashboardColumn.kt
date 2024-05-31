package com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun DashboardColumn() {
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
}