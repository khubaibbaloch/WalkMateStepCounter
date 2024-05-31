package com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import com.WalkMateApp.walkmate.ui.theme.Purple80
import com.WalkMateApp.walkmate.ui.theme.TwilightBlue

@Composable
fun ProfileItemCard(
    icon: Int = R.drawable.footstepsicon,
    mainText: String = "About you",
    valueText: String = "",
    smallText: String = "",
    iconSize: Dp = 20.dp,
    iconColor:Color = Color.Cyan,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(110.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TwilightBlue)
            .padding(16.dp)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(34.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MidnightBlue
                ),
                onClick = { /*TODO*/ })
            {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier.size(iconSize)
                )
            }

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = valueText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(.8f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = smallText,
                fontSize = 12.sp,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = mainText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}