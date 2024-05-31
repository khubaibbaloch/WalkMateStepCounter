package com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WalkMateApp.walkmate.R
import com.WalkMateApp.walkmate.ui.theme.MidnightBlue
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun SettingsItemCard(
    icon: Int = R.drawable.footstepsicon,
    iconColor: Color = Color.Cyan,
    iconSize: Dp = 20.dp,
    mainText:String = "",
    smallText:String = "",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TwilightBlue)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MidnightBlue),
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = iconColor,
                modifier = Modifier.size(iconSize),
            )
        }


        Spacer(modifier = Modifier.height(4.dp))

        Text(text = mainText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray)

        Text(text = smallText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,)
    }
}